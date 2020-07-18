package com.interview.chequer.web;

import com.interview.chequer.application.WorkspaceApplicationService;
import com.interview.chequer.web.dto.*;
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

    @PostMapping("{workspaceId}/members")
    public AddMemberResponse addMemberToWorkspace(
            @PathVariable long workspaceId,
            @RequestParam("userId") long userId,
            @RequestBody long memberId
    ) {
        return workspaceApplicationService.addMember(workspaceId, memberId);
    }

    @DeleteMapping("{workspaceId}/members/{memberId}")
    public RemoveMemberResponse removeMemberOfWorkspace(
            @PathVariable long workspaceId,
            @RequestParam("userId") long userId,
            @PathVariable long memberId
    ) {
        return workspaceApplicationService.removeMember(workspaceId, memberId);
    }

    @GetMapping("{workspaceId}/members")
    public MemberListResponse getMembersOfWorkspace(
            @PathVariable long workspaceId,
            @RequestParam("userId") long userId
    ) {
        return workspaceApplicationService.getMemberList(workspaceId);
    }
}
