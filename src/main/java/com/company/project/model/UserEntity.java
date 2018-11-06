package com.company.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USER_T")
@ToString(exclude = {"group", "tasks", "managedProjects", "account", "roles", "createdConversations"}, callSuper = true)
@Getter
@Setter
public class UserEntity extends AbstractEntity {
    private String name;
    private String surname;
    private String phone;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @OrderBy("id")
    private Set<TaskEntity> tasks = new LinkedHashSet<>();
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @OrderBy("id")
    private Set<ProjectEntity> managedProjects = new LinkedHashSet<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private AccountEntity account;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    //eager because one user can have only up to 3 roles
    @OrderBy("id")
    private Set<RoleEntity> roles = new LinkedHashSet<>();
    @OneToMany(mappedBy = "creator", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @OrderBy("id")
    private Set<ConversationEntity> createdConversations = new LinkedHashSet<>();

    public UserEntity() {
    }

    public void addRole(RoleEntity roleEntity) {
        this.roles.add(roleEntity);
    }

    public void removeRole(RoleEntity roleEntity) {
        this.roles.remove(roleEntity);
    }

    public void addConversation(ConversationEntity conversationEntity) {
        this.createdConversations.add(conversationEntity);
    }

    public void removeConversation(ConversationEntity conversationEntity) {
        this.createdConversations.remove(conversationEntity);
    }

    public void addToManagedProjects(ProjectEntity projectEntity) {
        this.managedProjects.add(projectEntity);
        projectEntity.setOwner(this);
    }

    public void removeFromManagedProjects(ProjectEntity projectEntity) {
        this.managedProjects.remove(projectEntity);
        projectEntity.setOwner(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return  Objects.equals(this.getId(),that.getId()) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, phone, birthDate,this.getId());

    }
}
