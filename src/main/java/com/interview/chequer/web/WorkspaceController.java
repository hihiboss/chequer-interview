package com.interview.chequer.web;

import com.interview.chequer.application.WorkspaceApplicationService;
import com.interview.chequer.web.dto.CreateWorkspaceRequest;
import com.interview.chequer.web.dto.CreateWorkspaceResponse;
import com.interview.chequer.web.dto.EditWorkspaceNameRequest;
import com.interview.chequer.web.dto.EditWorkspaceNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    final private WorkspaceApplicationService workspaceApplicationService;

    @PostMapping()
    public CreateWorkspaceResponse createWorkspace(@RequestBody CreateWorkspaceRequest request) {
        return workspaceApplicationService.createWorkspace(request);
    }

    @PutMapping("{workspaceId}")
    public EditWorkspaceNameResponse editWorkspaceName(@PathVariable long workspaceId, @RequestBody EditWorkspaceNameRequest request) {
        return workspaceApplicationService.editWorkspaceName(workspaceId, request);
    }
}
