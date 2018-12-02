package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
public class TableDto extends AbstractDto {
    private String name;
    private Long groupId;
    private Long projectId;
}
