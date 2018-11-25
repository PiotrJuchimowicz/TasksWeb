package com.company.project.mapper;

import com.company.project.dto.ConversationDto;
import com.company.project.exception.MapperException;
import com.company.project.model.ConversationEntity;
import org.springframework.stereotype.Component;

@Component
public class ConversationMapper implements AbstractMapper<ConversationEntity, ConversationDto> {
    @Override
    public void fromDtoToExistingEntity(ConversationDto dto, ConversationEntity entity) {
        if(dto==null || entity==null){
            throw new MapperException("Unable to map from ConversationDto to existing ConversationEntity");
        }
        if (dto.getId()!=null)
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
    }

    @Override
    public ConversationEntity fromDtoToNewEntity(ConversationDto dto) {
        if(dto==null){
            throw new MapperException("Unable to map from ConversationDto to new ConversationEntity");
        }
        ConversationEntity entity = new ConversationEntity();
        if (dto.getId()!=null)
            entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        return entity;
    }

    @Override
    public ConversationDto fromEntityToNewDto(ConversationEntity entity) {
        if(entity==null){
            throw new MapperException("Unable to map from ConversationEntity to ConversationDto");
        }
        ConversationDto dto = new ConversationDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
        return dto;
    }
}
