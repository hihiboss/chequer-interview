package com.interview.chequer.application;

import com.interview.chequer.domain.Workspace;
import com.interview.chequer.domain.WorkspaceRepository;
import com.interview.chequer.web.dto.CreateWorkspaceRequest;
import com.interview.chequer.web.dto.CreateWorkspaceResponse;
import com.interview.chequer.web.dto.EditWorkspaceNameRequest;
import com.interview.chequer.web.dto.EditWorkspaceNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkspaceApplicationService {
    final private WorkspaceRepository workspaceRepository;

    @Transactional
    public CreateWorkspaceResponse createWorkspace(CreateWorkspaceRequest request) {
        // TODO: validate owner
        // TODO: validate 5 workspaces criteria
        // TODO: validate workspace name criteria

        Workspace workspace = workspaceRepository.save(request.toEntity());

        return new CreateWorkspaceResponse(workspace);
    }

    @Transactional
    public EditWorkspaceNameResponse editWorkspaceName(long workspaceId, EditWorkspaceNameRequest request) {
        // TODO: validate owner
        // TODO: validate workspace name criteria

        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        workspace.changeName(request.getWorkspaceName());
        Workspace renamedWorkspace = workspaceRepository.save(workspace);

        return new EditWorkspaceNameResponse(renamedWorkspace);
    }
}
