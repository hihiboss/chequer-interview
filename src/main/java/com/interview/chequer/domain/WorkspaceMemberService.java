package com.interview.chequer.domain;

import com.interview.chequer.domain.exception.AlreadyExistingException;
import com.interview.chequer.domain.exception.ValidationException;
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
        if (workspaceMemberRepository.existsByWorkspaceAndMember(workspaceMember.getWorkspace(), workspaceMember.getMember())) {
            throw new AlreadyExistingException("That user is already added to workspace.");
        }
    }

    private void validateMemberCount(WorkspaceMember workspaceMember) {
        if (workspaceMemberRepository.countAllByWorkspace(workspaceMember.getWorkspace()) >= 10) {
            throw new ValidationException("Workspace can have 10 members at most.");
        }
    }
}
