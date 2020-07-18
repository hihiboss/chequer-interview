package com.interview.chequer.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkspaceMemberService {
    final private WorkspaceMemberRepository workspaceMemberRepository;

    public WorkspaceMember addWorkspaceMember(Workspace workspace, User member) {
        WorkspaceMember workspaceMember = WorkspaceMember.builder()
                .workspace(workspace)
                .member(member)
                .build();

        validateUser(workspaceMember);
        validateMemberCount(workspaceMember);

        return workspaceMember;
    }

    private void validateUser(WorkspaceMember workspaceMember) {
        long workspaceId = workspaceMember.getWorkspaceId();
        long userId = workspaceMember.getMemberId();

        if (workspaceMemberRepository.existsByWorkspaceIdAndMemberId(workspaceId, userId)) {
            throw new IllegalStateException("That user is already added to workspace.");
        }
    }

    private void validateMemberCount(WorkspaceMember workspaceMember) {
        if (workspaceMemberRepository.countAllByWorkspaceId(workspaceMember.getWorkspaceId()) >= 10) {
            throw new IllegalStateException("Workspace can have 10 members at most.");
        }
    }
}
