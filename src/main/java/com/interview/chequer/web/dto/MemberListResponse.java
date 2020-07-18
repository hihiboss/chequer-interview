package com.interview.chequer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberListResponse {
    private long workspaceId;
    private List<Long> memberList;
}
