package com.interview.chequer.domain;

import com.interview.chequer.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class WorkspaceTest {
    @Test
    void validateTest_shouldFail_byTooLongName() {
        // given
        String name = createString('t', 501);
        Workspace workspace = Workspace.builder()
                .name(name)
                .build();

        // when
        ValidationException e = assertThrows(ValidationException.class, workspace::validate);
    }

    @Test
    void changeNameTest_shouldSuccess() {
        // given
        String beforeName = "before test";
        String testName = "test";
        Workspace workspace = Workspace.builder()
                .name(beforeName)
                .build();

        // when
        workspace.changeName(testName);

        // then
        String workspaceName = workspace.getName();
        assertThat(workspaceName).isNotEqualTo(beforeName);
        assertThat(workspaceName).isEqualTo(testName);
    }

    private String createString(char character, int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, character);
        return new String(chars);
    }
}
