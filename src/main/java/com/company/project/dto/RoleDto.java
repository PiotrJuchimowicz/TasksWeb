package com.company.project.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoleDto extends AbstractDto {
    private String roleName;
    private long userId;
}
