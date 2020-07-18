package com.interview.chequer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddMemberResponse {
    private long workspaceId;
    private long addedMemberId;
    private int memberCount;
}
