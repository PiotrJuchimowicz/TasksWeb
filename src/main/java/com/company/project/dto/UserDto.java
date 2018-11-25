package com.company.project.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@ToString(callSuper = true)
@Getter
@Setter
public class UserDto extends AbstractDto {
    private String name;
    private String surname;
    private String phone;
    private AccountDto account;
    private List<RoleDto> roles;

    public void addRole(RoleDto roleDto){
        roles.add(roleDto);
    }
}
