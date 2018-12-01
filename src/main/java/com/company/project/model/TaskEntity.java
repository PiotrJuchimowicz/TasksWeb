package com.company.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TASK_T")
@ToString(exclude = "users", callSuper = true)
@Setter
@Getter
public class TaskEntity extends AbstractEntity {
    private String name;
    private String description;

    public enum Priority {
        HIGH, MEDIUM, SMALL
    }

    @Enumerated(value = EnumType.STRING)
    private Priority priority;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TASK_USER",
            joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @OrderBy("id")
    private Set<UserEntity> users = new LinkedHashSet<>();

    public void addUser(UserEntity userEntity) {
        this.users.add(userEntity);
        userEntity.getTasks().add(this);
    }

    public void removeUser(UserEntity userEntity) {
        this.users.remove(userEntity);
        userEntity.getTasks().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof TaskEntity)) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, priority, this.getId());

    }
}
