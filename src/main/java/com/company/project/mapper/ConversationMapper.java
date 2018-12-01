package com.company.project.mapper;

import com.company.project.dto.ConversationDto;
import com.company.project.exception.MapperException;
import com.company.project.model.ConversationEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ConversationMapper implements AbstractMapper<ConversationEntity, ConversationDto> {
    @Override
    public void fromDtoToExistingEntity(ConversationDto dto, ConversationEntity entity) {
        if (dto == null || entity == null) {
            throw new MapperException("Unable to map from ConversationDto to existing ConversationEntity");
        }
        LocalDateTime creationDate = dto.getCreationDate();
        if(creationDate!=null){
            throw new MapperException("Creation date can not be changed");
        }
        String title = dto.getTitle();
        if(title!=null)
        entity.setTitle(title);
    }

    @Override
    public ConversationEntity fromDtoToNewEntity(ConversationDto dto) {
        if (dto == null) {
            throw new MapperException("Unable to map from ConversationDto to new ConversationEntity");
        }
        if(dto.getCreationDate()!=null){
            throw new MapperException("Creation date is generated automatically");
        }
        ConversationEntity entity = new ConversationEntity();
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(LocalDateTime.now());
        return entity;
    }

    @Override
    public ConversationDto fromEntityToNewDto(ConversationEntity entity) {
        if (entity == null) {
            throw new MapperException("Unable to map from ConversationEntity to ConversationDto");
        }
        ConversationDto dto = new ConversationDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
        return dto;
    }
}
