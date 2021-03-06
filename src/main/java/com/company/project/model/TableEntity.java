package com.company.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TABLE_T")
@ToString(exclude = {"group", "project"}, callSuper = true)
@Setter
@Getter
public class TableEntity extends AbstractEntity {
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private GroupEntity group;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof TableEntity)) return false;
        TableEntity that = (TableEntity) o;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, this.getId());
    }
}
