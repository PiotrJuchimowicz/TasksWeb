package com.company.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PROJECT_T")
@ToString(exclude = {"tables", "owner","tasks"}, callSuper = true)
@Setter
@Getter
public class ProjectEntity extends AbstractEntity {
    private String name;
    private String description;
    @OneToMany(mappedBy = "projectEntity",cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TaskEntity> tasks = new LinkedHashSet<>();
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("id")
    private Set<TableEntity> tables = new LinkedHashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    public ProjectEntity() {
    }


    public void addTask(TaskEntity taskEntity) {
        this.tasks.add(taskEntity);
        taskEntity.setProjectEntity(this);
    }

    public void removeTask(TaskEntity taskEntity) {
        this.tasks.remove(taskEntity);
        taskEntity.setProjectEntity(null);
    }

    public void addTable(TableEntity tableEntity) {
        this.tables.add(tableEntity);
        tableEntity.setProject(this);
    }

    public void removeTable(TableEntity tableEntity) {
        this.tables.remove(tableEntity);
        tableEntity.setProject(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof ProjectEntity)) return false;
        ProjectEntity that = (ProjectEntity) o;
        return
                Objects.equals(this.getId(), that.getId()) &&
                        Objects.equals(name, that.name) &&
                        Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, this.getId());
    }
}
