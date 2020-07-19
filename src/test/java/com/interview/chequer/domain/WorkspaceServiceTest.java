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
public class WorkspaceServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private WorkspaceService workspaceService;

    private User user;
    private String workspaceName;

    @BeforeEach
    void setup() {
        user = userRepository.save(new User());
        workspaceName = "test workspace";
    }

    @Test
    void createTest_shouldSuccess() {
        // given
        String workspaceName = "test workspace";

        // when
        Workspace workspace = workspaceService.create(user, workspaceName);

        // then
        assertThat(workspace.getOwnerId()).isEqualTo(user.getId());
    }

    @Test
    void createTest_shouldFail_byWorkspaceCount() {
        // given
        for (int i = 0; i < 5; i++) {
            workspaceRepository.save(Workspace.builder()
                    .ownerId(user.getId())
                    .build()
            );
        }

        // when
        assertThrows(ValidationException.class, ()->workspaceService.create(user, workspaceName));
    }
}
