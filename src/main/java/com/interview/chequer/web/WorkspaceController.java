package com.interview.chequer.web;

import com.interview.chequer.application.WorkspaceApplicationService;
import com.interview.chequer.web.dto.CreateWorkspaceResponse;
import com.interview.chequer.web.dto.EditWorkspaceNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    final private WorkspaceApplicationService workspaceApplicationService;

    @PostMapping()
    public CreateWorkspaceResponse createWorkspace(
            @RequestParam("userId") long userId,
            @RequestBody String workspaceName
    ) {
        return workspaceApplicationService.createWorkspace(userId, workspaceName);
    }

    @PutMapping("{workspaceId}")
    public EditWorkspaceNameResponse editWorkspaceName(
            @PathVariable long workspaceId,
            @RequestParam("userId") long userId,
            @RequestBody String workspaceName
    ) {
        return workspaceApplicationService.editWorkspaceName(workspaceId, workspaceName);
    }
}
