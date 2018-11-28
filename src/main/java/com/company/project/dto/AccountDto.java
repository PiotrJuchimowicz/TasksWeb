package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
public class AccountDto extends AbstractDto {
    private String email;
    private String password;
    private long userId;
    //in json property name is: active
    private Boolean isActive;
}
