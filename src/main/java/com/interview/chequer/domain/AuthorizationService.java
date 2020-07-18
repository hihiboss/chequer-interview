package com.interview.chequer.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    final private WorkspaceRepository workspaceRepository;

    public void validateWorkspaceOwner(long userId, long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Can not find workspace with id %s.", workspaceId)));

        if (!workspace.isOwner(userId)) {
            throw new IllegalArgumentException("You are not the owner of workspace.");
        }
    }
}
