package com.interview.chequer.web.dto;

import com.interview.chequer.domain.Workspace;
import lombok.Getter;

@Getter
public class CreateWorkspaceResponse {
    private long workspaceId;
    private String workspaceName;
    private long ownerId;

    public CreateWorkspaceResponse(Workspace entity) {
        this.workspaceId = entity.getId();
        this.workspaceName = entity.getName();
        this.ownerId = entity.getOwnerId();
    }
}
