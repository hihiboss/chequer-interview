package com.interview.chequer.domain;

import com.interview.chequer.domain.exception.NotFoundException;
import com.interview.chequer.domain.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    final private WorkspaceRepository workspaceRepository;

    public void validateWorkspaceOwner(long userId, long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new NotFoundException(String.format("Can not find workspace with id %s.", workspaceId)));

        if (!workspace.isOwner(userId)) {
            throw new UnauthorizedException("You are not the owner of workspace.");
        }
    }
}
