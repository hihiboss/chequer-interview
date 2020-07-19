package com.interview.chequer.web.dto;

import com.interview.chequer.domain.User;
import com.interview.chequer.domain.WorkspaceMember;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberListResponse {
    private long workspaceId;
    private List<Member> memberList;

    @AllArgsConstructor
    @Getter
    static class Member {
        private long id;
    }

    public MemberListResponse(long workspaceId, List<WorkspaceMember> workspaceMemberList) {
        this.workspaceId = workspaceId;
        this.memberList = workspaceMemberList.stream()
                .map(WorkspaceMember::getMember)
                .map(User::getId)
                .map(Member::new)
                .collect(Collectors.toList());
    }
}
