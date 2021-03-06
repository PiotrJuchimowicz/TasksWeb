package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@ToString(callSuper = true)
@Getter
@Setter
public class AccountDto extends AbstractDto {
    private String email;
    private String password;
    private long userId;
    private Boolean isActive;
}
