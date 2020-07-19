package com.interview.chequer.domain;

import com.interview.chequer.domain.exception.ValidationException;
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

    @Setter(value = AccessLevel.PRIVATE)
    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "owner_id")
    private long ownerId;

    public void validate() {
        if (this.hasTooLongName()) {
            throw new ValidationException("Workspace name should be less than 500 characters.");
        }
    }

    public void changeName(String name) {
        this.name = name;
        this.validate();
    }

    public Boolean isOwner(long userId) {
        return this.ownerId == userId;
    }

    private boolean hasTooLongName() {
        return this.name.length() > 500;
    }
}
