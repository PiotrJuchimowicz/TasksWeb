package com.company.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ROLE_T", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_name"}))
@Getter
@Setter
@ToString(exclude = "user", callSuper = true)
public class RoleEntity extends AbstractEntity {

    public enum Role {
        DEVELOPER, TESTER, MANAGER
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private Role roleValue;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof RoleEntity)) return false;
        RoleEntity that = (RoleEntity) o;
        return roleValue == that.roleValue && Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleValue, user, this.getId());
    }
}
