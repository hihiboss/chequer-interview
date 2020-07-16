package com.interview.chequer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditWorkspaceNameRequest {
    private long ownerId;
    private String workspaceName;
}
