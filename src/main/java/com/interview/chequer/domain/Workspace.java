package com.interview.chequer.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "workspace")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "owner_id")
    private long ownerId;

    public void changeName(String name) {
        this.name = name;
    }
}
