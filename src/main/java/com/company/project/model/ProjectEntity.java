package com.company.project.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PROJECT_T")
@ToString(exclude = {"tables","owner"},callSuper = true)
@Setter
@Getter
public class ProjectEntity extends AbstractEntity {
    private String name;
    private String description;
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<TableEntity> tables = new LinkedHashSet<>();
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    public ProjectEntity() {
    }

    public void addTable(TableEntity tableEntity){
        this.tables.add(tableEntity);
        tableEntity.setProject(this);
    }

    public void removeTable(TableEntity tableEntity){
        this.tables.remove(tableEntity);
        tableEntity.setProject(null);
    }

    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if (this == o) return true;
        if (!(o instanceof ProjectEntity)) return false;
        ProjectEntity that = (ProjectEntity) o;
        return
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
