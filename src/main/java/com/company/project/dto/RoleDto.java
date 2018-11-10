package com.company.project.dto;

import lombok.*;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoleDto extends AbstractDto {
    private String roleName;
}
