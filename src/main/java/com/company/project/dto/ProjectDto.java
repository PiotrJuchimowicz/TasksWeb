package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
public class ProjectDto extends AbstractDto {
    private String name;
    private String description;
    private Long ownerId;
}
