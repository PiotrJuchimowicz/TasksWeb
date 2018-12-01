package com.company.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USER_T")
@ToString(exclude = {"group", "tasks", "managedProjects", "account", "roles"}, callSuper = true)
@Getter
@Setter
public class UserEntity extends AbstractEntity {
    private String name;
    private String surname;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;
    @ManyToMany(mappedBy = "users",fetch = FetchType.LAZY)
    @OrderBy("id")
    private Set<TaskEntity> tasks = new LinkedHashSet<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("id")
    private Set<ProjectEntity> managedProjects = new LinkedHashSet<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private AccountEntity account;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",
            orphanRemoval = true, fetch = FetchType.EAGER)
    //eager because one user can have only up to 3 roles
    @OrderBy("id")
    private Set<RoleEntity> roles = new LinkedHashSet<>();

    public UserEntity() {
    }

    public void addRole(RoleEntity roleEntity) {
        this.roles.add(roleEntity);
        roleEntity.setUser(this);
    }

    public void removeRole(RoleEntity roleEntity) {
        this.roles.remove(roleEntity);
        roleEntity.setUser(null);
    }

    public void addToManagedProjects(ProjectEntity projectEntity) {
        this.managedProjects.add(projectEntity);
        projectEntity.setOwner(this);
    }

    public void removeFromManagedProjects(ProjectEntity projectEntity) {
        this.managedProjects.remove(projectEntity);
        projectEntity.setOwner(null);
    }
    public void addTask(TaskEntity taskEntity ) {
        this.tasks.add(taskEntity);
        taskEntity.getUsers().add(this);
    }

    public void removeTask(TaskEntity taskEntity) {
        this.tasks.remove(taskEntity);
        taskEntity.getUsers().remove(this);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, phone, this.getId());

    }
}
