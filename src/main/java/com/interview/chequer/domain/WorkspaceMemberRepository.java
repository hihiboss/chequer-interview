package com.interview.chequer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember, Long> {
    Optional<WorkspaceMember> findByWorkspaceAndMember(Workspace workspace, User member);

    List<WorkspaceMember> findAllByWorkspace(Workspace workspace);

    Boolean existsByWorkspaceAndMember(Workspace workspace, User member);

    int countAllByWorkspace(Workspace workspace);
}
