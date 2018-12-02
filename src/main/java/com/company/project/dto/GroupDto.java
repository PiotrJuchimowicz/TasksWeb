package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString(callSuper = true)
@Getter
@Setter
public class GroupDto extends AbstractDto {
    private String name;
    private Long userId;
    private Set<UserDto> users;
}
