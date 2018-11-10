package com.company.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
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
    private LocalDate birthDate;
    private AccountDto accountDto;

    private Set<RoleDto> roleDtos = new LinkedHashSet<>();

    public void addRole(RoleDto roleDto){
        roleDtos.add(roleDto);
    }
}