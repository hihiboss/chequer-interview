package com.interview.chequer.application;

import com.interview.chequer.domain.*;
import com.interview.chequer.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceApplicationService {
    final private WorkspaceRepository workspaceRepository;
    final private UserRepository userRepository;
    final private WorkspaceMemberRepository workspaceMemberRepository;

    final private WorkspaceService workspaceService;
    final private WorkspaceMemberService workspaceMemberService;

    @Transactional
    public CreateWorkspaceResponse createWorkspace(long userId, String workspaceName) {
        User owner = getUser(userId);

        Workspace workspace = workspaceService.create(owner, workspaceName);
        workspaceRepository.save(workspace);

        return new CreateWorkspaceResponse(workspace);
    }

    @Transactional
    public EditWorkspaceNameResponse editWorkspaceName(long workspaceId, String workspaceName) {
        Workspace workspace = getWorkspace(workspaceId);

        workspace.changeName(workspaceName);
        workspaceRepository.save(workspace);

        return new EditWorkspaceNameResponse(workspace);
    }

    @Transactional
    public AddMemberResponse addMember(long workspaceId, long memberId) {
        Workspace workspace = getWorkspace(workspaceId);
        User member = getUser(memberId);

        WorkspaceMember workspaceMember = workspaceMemberService.addWorkspaceMember(workspace, member);
        workspaceMemberRepository.save(workspaceMember);

        return new AddMemberResponse(workspaceId, memberId, getMemberCount(workspaceId));
    }

    @Transactional
    public RemoveMemberResponse removeMember(long workspaceId, long memberId) {
        WorkspaceMember workspaceMember = getWorkspaceMember(workspaceId, memberId);

        workspaceMemberRepository.delete(workspaceMember);

        return new RemoveMemberResponse(workspaceId, memberId, getMemberCount(workspaceId));
    }

    @Transactional
    public MemberListResponse getMemberList(long workspaceId) {
        Workspace workspace = getWorkspace(workspaceId);

        List<WorkspaceMember> workspaceMembers = workspaceMemberRepository.findAllByWorkspace(workspace);

        return new MemberListResponse(
                workspaceId,
                workspaceMembers.stream()
                        .map(WorkspaceMember::getMember)
                        .map(User::getId)
                        .collect(Collectors.toList())
        );
    }

    private Workspace getWorkspace(long workspaceId) {
        return workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Can not find workspace with id %s.", workspaceId)));
    }

    private User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Can not find user with id %s.", userId)));
    }

    private int getMemberCount(long workspaceId) {
        Workspace workspace = getWorkspace(workspaceId);
        return workspaceMemberRepository.countAllByWorkspace(workspace);
    }

    private WorkspaceMember getWorkspaceMember(long workspaceId, long userId) {
        Workspace workspace = getWorkspace(workspaceId);
        User member = getUser(userId);

        return workspaceMemberRepository.findByWorkspaceAndMember(workspace, member)
                .orElseThrow(() -> new IllegalStateException("That user is not added to workspace."));
    }
}
