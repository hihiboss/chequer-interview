package com.interview.chequer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember, Long> {
    Optional<WorkspaceMember> findByWorkspaceIdAndMemberId(long workspaceId, long memberId);
    List<WorkspaceMember> findAllByWorkspaceId(long workspaceId);
}
