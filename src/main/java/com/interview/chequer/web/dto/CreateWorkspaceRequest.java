package com.interview.chequer.web.dto;

import com.interview.chequer.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateWorkspaceRequest {
    private long ownerId;
    private String workspaceName;

    public Workspace toEntity() {
        return Workspace.builder()
                .ownerId(ownerId)
                .name(workspaceName)
                .build();
    }
}
