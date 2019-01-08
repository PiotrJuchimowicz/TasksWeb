package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString(callSuper = true)
@Getter
@Setter
public class TaskDto extends AbstractDto {
    private String name;
    private String description;
    private String priority;
    private String userEmail;
    private Long projectId;
    private Boolean isDone;
    private Set<UserDto> users;

}
