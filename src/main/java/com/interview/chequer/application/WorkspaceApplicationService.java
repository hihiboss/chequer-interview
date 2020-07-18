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

    final private ValidationService validationService;

    @Transactional
    public CreateWorkspaceResponse createWorkspace(long userId, String workspaceName) {
        validationService.validateWorkspaceCount(userId);
        validationService.validateWorkspaceName(workspaceName);

        Workspace workspace = workspaceRepository.save(
                Workspace.builder()
                        .ownerId(userId)
                        .name(workspaceName)
                        .build()
        );

        return new CreateWorkspaceResponse(workspace);
    }

    @Transactional
    public EditWorkspaceNameResponse editWorkspaceName(long workspaceId, String workspaceName) {
        validationService.validateWorkspaceName(workspaceName);

        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        workspace.changeName(workspaceName);
        Workspace renamedWorkspace = workspaceRepository.save(workspace);

        return new EditWorkspaceNameResponse(renamedWorkspace);
    }

    @Transactional
    public AddMemberResponse addMember(long workspaceId, long memberId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        List<WorkspaceMember> workspaceMembers = workspaceMemberRepository.findAllByWorkspaceId(workspaceId);
        if (workspaceMembers.size() >= 10) {
            throw new IllegalArgumentException("");
        }

        workspaceMemberRepository.save(
                WorkspaceMember.builder()
                        .workspace(workspace)
                        .member(user)
                        .build()
        );

        workspaceMembers = workspaceMemberRepository.findAllByWorkspaceId(workspaceId);

        return new AddMemberResponse(workspaceId, memberId, workspaceMembers.size());
    }

    @Transactional
    public RemoveMemberResponse removeMember(long workspaceId, long memberId) {
        if (!workspaceRepository.existsById(workspaceId)) {
            throw new IllegalArgumentException("");
        }
        if (!userRepository.existsById(memberId)) {
            throw new IllegalArgumentException("");
        }

        WorkspaceMember workspaceMember = workspaceMemberRepository.findByWorkspaceIdAndMemberId(workspaceId, memberId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        workspaceMemberRepository.delete(workspaceMember);

        List<WorkspaceMember> workspaceMembers = workspaceMemberRepository.findAllByWorkspaceId(workspaceId);

        return new RemoveMemberResponse(workspaceId, memberId, workspaceMembers.size());
    }

    @Transactional
    public MemberListResponse getMemberList(long workspaceId) {
        if (!workspaceRepository.existsById(workspaceId)) {
            throw new IllegalArgumentException("");
        }

        List<WorkspaceMember> workspaceMembers = workspaceMemberRepository.findAllByWorkspaceId(workspaceId);

        return new MemberListResponse(
                workspaceId,
                workspaceMembers.stream()
                        .map(m -> m.getMember().getId())
                        .collect(Collectors.toList())
        );
    }
}
