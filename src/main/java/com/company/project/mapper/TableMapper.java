package com.company.project.mapper;

import com.company.project.dto.TableDto;
import com.company.project.exception.MapperException;
import com.company.project.model.TableEntity;
import com.company.project.service.ProjectService;
import com.company.project.service.TableService;
import org.springframework.stereotype.Component;

@Component
public class TableMapper implements AbstractMapper<TableEntity, TableDto> {
    private ProjectService projectService;
    private TableService tableService;
    @Override
    public void fromDtoToExistingEntity(TableDto tableDto, TableEntity tableEntity) {
        if(tableDto==null || tableEntity==null){
            throw new MapperException("Unable to map from TableDto to existing TableEntity");
        }
        String name = tableDto.getName();
        if(name!=null){
            tableEntity.setName(name);
        }
        Long groupId = tableDto.getGroupId();
    }

    @Override
    public TableEntity fromDtoToNewEntity(TableDto tableDto) {
        return null;
    }

    @Override
    public TableDto fromEntityToNewDto(TableEntity tableEntity) {
        return null;
    }
}
