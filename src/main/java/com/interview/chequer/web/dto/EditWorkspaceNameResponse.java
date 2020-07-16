package com.interview.chequer.web.dto;

import com.interview.chequer.domain.Workspace;
import lombok.Getter;

@Getter
public class EditWorkspaceNameResponse {
    private long workspaceId;
    private String workspaceName;
    private long ownerId;

    public EditWorkspaceNameResponse(Workspace entity) {
        this.workspaceId = entity.getId();
        this.workspaceName = entity.getName();
        this.ownerId = entity.getOwnerId();
    }
}
