package com.interview.chequer.application;

import com.interview.chequer.domain.*;
import com.interview.chequer.domain.exception.NotFoundException;
import com.interview.chequer.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceApplicationService {
    final private WorkspaceRepository workspaceRepository;
    final private UserRepository userRepository;
    final private WorkspaceMemberRepository workspaceMemberRepository;

    final private WorkspaceService workspaceService;
    final private WorkspaceMemberService workspaceMemberService;

    @Transactional
    public CreateWorkspaceResponse createWorkspace(long userId, CreateWorkspaceRequest request) {
        User owner = getUser(userId);

        Workspace workspace = workspaceService.create(owner, request.getWorkspaceName());
        workspaceRepository.save(workspace);

        return new CreateWorkspaceResponse(workspace);
    }

    @Transactional
    public EditWorkspaceNameResponse editWorkspaceName(long workspaceId, EditWorkspaceNameRequest request) {
        Workspace workspace = getWorkspace(workspaceId);

        workspace.changeName(request.getWorkspaceName());
        workspaceRepository.save(workspace);

        return new EditWorkspaceNameResponse(workspace);
    }

    @Transactional
    public AddMemberResponse addMember(long workspaceId, AddMemberRequest request) {
        Workspace workspace = getWorkspace(workspaceId);
        User member = getUser(request.getMemberId());

        WorkspaceMember workspaceMember = workspaceMemberService.addWorkspaceMember(workspace, member);
        workspaceMemberRepository.save(workspaceMember);

        return new AddMemberResponse(workspaceId, member.getId(), getMemberCount(workspaceId));
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

        return new MemberListResponse(workspaceId, workspaceMembers);
    }

    private Workspace getWorkspace(long workspaceId) {
        return workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new NotFoundException(String.format("Can not find workspace with id %s.", workspaceId)));
    }

    private User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Can not find user with id %s.", userId)));
    }

    private int getMemberCount(long workspaceId) {
        Workspace workspace = getWorkspace(workspaceId);
        return workspaceMemberRepository.countAllByWorkspace(workspace);
    }

    private WorkspaceMember getWorkspaceMember(long workspaceId, long userId) {
        Workspace workspace = getWorkspace(workspaceId);
        User member = getUser(userId);

        return workspaceMemberRepository.findByWorkspaceAndMember(workspace, member)
                .orElseThrow(() -> new NotFoundException("That user is not added to workspace."));
    }
}
