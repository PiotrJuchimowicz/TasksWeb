package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@ToString(callSuper = true)
@Getter
@Setter
public class UserDto extends AbstractDto {
    private String name;
    private String surname;
    private String phone;
    private AccountDto account;
    private List<RoleDto> roles = new LinkedList<>();

    public void addRole(RoleDto roleDto) {
        roles.add(roleDto);
    }
}
