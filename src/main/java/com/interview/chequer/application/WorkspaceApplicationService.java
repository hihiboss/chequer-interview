package com.interview.chequer.application;

import com.interview.chequer.domain.ValidationService;
import com.interview.chequer.domain.Workspace;
import com.interview.chequer.domain.WorkspaceRepository;
import com.interview.chequer.web.dto.CreateWorkspaceResponse;
import com.interview.chequer.web.dto.EditWorkspaceNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkspaceApplicationService {
    final private WorkspaceRepository workspaceRepository;
    final private ValidationService validationService;

    @Transactional
    public CreateWorkspaceResponse createWorkspace(long userId, String workspaceName) {
        validationService.validateWorkspaceCount(userId);
        validationService.validateWorkspaceName(workspaceName);

        Workspace workspace = workspaceRepository.save(
                Workspace.builder()
                .ownerId(userId)
                .name(workspaceName)
                .build()
        );

        return new CreateWorkspaceResponse(workspace);
    }

    @Transactional
    public EditWorkspaceNameResponse editWorkspaceName(long workspaceId, String workspaceName) {
        validationService.validateWorkspaceName(workspaceName);

        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        workspace.changeName(workspaceName);
        Workspace renamedWorkspace = workspaceRepository.save(workspace);

        return new EditWorkspaceNameResponse(renamedWorkspace);
    }
}
