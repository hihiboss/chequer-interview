package com.interview.chequer.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    final private WorkspaceRepository workspaceRepository;

    public Workspace create(User owner, String workspaceName) {
        Workspace workspace = Workspace.builder()
                .ownerId(owner.getId())
                .name(workspaceName)
                .build();

        validateWorkspaceCount(workspace);
        workspace.validate();

        return workspace;
    }

    private void validateWorkspaceCount(Workspace workspace) {
        if (workspaceRepository.countAllByOwnerId(workspace.getOwnerId()) >= 5) {
            throw new IllegalStateException("User can have 5 workspaces at most.");
        }
    }
}
