package com.company.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "GROUP_OF_USERS_T")
@ToString(exclude = {"tables", "usersInGroup"}, callSuper = true)
@Setter
@Getter
public class GroupEntity extends AbstractEntity {
    private String name;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id")
    private Set<UserEntity> usersInGroup = new LinkedHashSet<>();
    @OrderBy("id")
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TableEntity> tables = new LinkedHashSet<>();

    public GroupEntity() {
    }

    public void addUserToGroup(UserEntity userEntity) {
        this.usersInGroup.add(userEntity);
        userEntity.setGroup(this);
    }

    public void removeUserFromGroup(UserEntity userEntity) {
        this.usersInGroup.remove(userEntity);
        userEntity.setGroup(null);
    }

    public void addTable(TableEntity tableEntity) {
        this.tables.add(tableEntity);
        tableEntity.setGroup(this);
    }

    public void removeTable(TableEntity tableEntity) {
        this.tables.remove(tableEntity);
        tableEntity.setGroup(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof GroupEntity)) return false;
        GroupEntity that = (GroupEntity) o;
        return
                Objects.equals(this.getId(), that.getId()) &&
                        Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, this.getId());
    }
}
