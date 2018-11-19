/*
package com.company.project.mapper;

import com.company.project.dto.ConversationDto;
import com.company.project.model.ConversationEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
public class ConversationMapper implements AbstractMapper<ConversationEntity, ConversationDto> {
    @Override
    public void fromDtoToEntity(ConversationDto dto,ConversationEntity conversationEntity) {
        conversationEntity.setId(dto.getId());
        conversationEntity.setTitle(dto.getTitle());

        LocalDateTime localDateTime = convertJsonFormatToLocalDateTime(dto);
        conversationEntity.setCreationDate(localDateTime);
    }

    @Override
    public void fromEntityToDto(ConversationEntity entity,ConversationDto conversationDto){
        conversationDto.setId(entity.getId());
        //conversationDto.setCreationDate(entity.getCreationDate());
        conversationDto.setTitle(entity.getTitle());
    }

    private LocalDateTime convertJsonFormatToLocalDateTime(ConversationDto dto) {
        LocalDate localDate = dto.getCreationDate();
        int year = dto.getCreationDate().getYear();
        int month = dto.getCreationDate().getMonth().getValue();
        int day = dto.getCreationDate().getDayOfMonth();
        int hour = dto.getHour();
        int minute = dto.getMinute();
        int second = dto.getSecond();
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        return localDateTime;
    }
}
*/
