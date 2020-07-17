package com.interview.chequer.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidationService {
    final private WorkspaceRepository workspaceRepository;

    @Transactional(readOnly = true)
    public void validateWorkspaceOwner(long userId, long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(()->new IllegalArgumentException(""));

        if(userId != workspace.getOwnerId()) {
            throw new IllegalArgumentException("");
        }
    }

    public void validateWorkspaceName(String workspaceName) {
        if(workspaceName.length() > 500) {
            throw new IllegalArgumentException("");
        }
    }

    @Transactional(readOnly = true)
    public void validateWorkspaceCount(long userId) {
        List<Workspace> workspaceList = workspaceRepository.findByOwnerId(userId);
        if(workspaceList.size() >= 5) {
            throw new IllegalArgumentException("");
        }
    }
}
