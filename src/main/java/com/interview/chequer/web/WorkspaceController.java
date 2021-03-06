package com.interview.chequer.web;

import com.interview.chequer.application.WorkspaceApplicationService;
import com.interview.chequer.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    final private WorkspaceApplicationService workspaceApplicationService;

    @PostMapping()
    public CreateWorkspaceResponse createWorkspace(
            @RequestParam("userId") long userId,
            @RequestBody CreateWorkspaceRequest request
    ) {
        return workspaceApplicationService.createWorkspace(userId, request);
    }

    @PatchMapping("{workspaceId}")
    public EditWorkspaceNameResponse editWorkspaceName(
            @PathVariable long workspaceId,
            @RequestParam("userId") long userId,
            @RequestBody EditWorkspaceNameRequest request
    ) {
        return workspaceApplicationService.editWorkspaceName(workspaceId, request);
    }

    @PostMapping("{workspaceId}/members")
    public AddMemberResponse addMemberToWorkspace(
            @PathVariable long workspaceId,
            @RequestParam("userId") long userId,
            @RequestBody AddMemberRequest request
    ) {
        return workspaceApplicationService.addMember(workspaceId, request);
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
            @PathVariable long workspaceId
    ) {
        return workspaceApplicationService.getMemberList(workspaceId);
    }
}
