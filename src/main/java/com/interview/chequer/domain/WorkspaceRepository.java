package com.interview.chequer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    List<Workspace> findByOwnerId(long ownerId);
}
