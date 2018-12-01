package com.company.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ACCOUNT_T")
@Getter
@Setter
@ToString(exclude = "user", callSuper = true)
public class AccountEntity extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Column(name = "is_active")
    private boolean isActive = false;
    @OneToOne(mappedBy = "account", fetch = FetchType.EAGER)
    private UserEntity user;

    public void addUser(UserEntity userEntity) {
        this.setUser(userEntity);
        userEntity.setAccount(this);
    }

    public void removeUser(UserEntity userEntity) {
        this.setUser(null);
        userEntity.setAccount(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof AccountEntity)) return false;
        AccountEntity that = (AccountEntity) o;
        return
                Objects.equals(this.getId(), that.getId()) &&
                        Objects.equals(email, that.email) &&
                        Objects.equals(password, that.password) &&
                        Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, isActive, this.getId());
    }
}
