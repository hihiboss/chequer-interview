package com.interview.chequer.domain;

import com.interview.chequer.domain.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class WorkspaceMemberServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private WorkspaceMemberRepository workspaceMemberRepository;

    @Autowired
    private WorkspaceMemberService workspaceMemberService;

    private User owner;
    private Workspace workspace;

    @BeforeEach
    void setup() {
        owner = userRepository.save(new User());
        workspace = workspaceRepository.save(
                Workspace.builder()
                        .ownerId(owner.getId())
                        .build()
        );
    }

    @Test
    void addWorkspaceMemberTest_shouldSuccess() {
        // given
        User member = userRepository.save(new User());

        // when
        WorkspaceMember workspaceMember = workspaceMemberService.addWorkspaceMember(workspace, member);

        // then
        assertThat(workspaceMember.getMember().getId()).isEqualTo(member.getId());
        assertThat(workspaceMember.getWorkspace().getOwnerId()).isEqualTo(owner.getId());
    }

    @Test
    void addWorkspaceMemberTest_shouldFail_byMemberCount() {
        // given
        for (int i = 0; i < 10; i++) {
            User user = userRepository.save(new User());
            workspaceMemberRepository.save(
                    workspaceMemberService.addWorkspaceMember(workspace, user)
            );
        }
        User member = userRepository.save(new User());

        // when
        assertThrows(ValidationException.class, () -> workspaceMemberService.addWorkspaceMember(workspace, member));
    }
}
