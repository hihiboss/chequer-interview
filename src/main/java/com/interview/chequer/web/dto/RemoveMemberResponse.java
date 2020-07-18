package com.interview.chequer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RemoveMemberResponse {
    private long workspaceId;
    private long removedMemberId;
    private int memberCount;
}
