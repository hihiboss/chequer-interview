package com.interview.chequer.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "workspace_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class WorkspaceMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    public long getMemberId() {
        return this.member.getId();
    }

    public long getWorkspaceId() {
        return this.workspace.getId();
    }
}
