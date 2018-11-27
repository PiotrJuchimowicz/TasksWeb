package com.company.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@ToString(callSuper = true)
@Getter
@Setter
public class ConversationDto extends AbstractDto {
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime creationDate;
    private List<MessageDto> messages = new LinkedList<>();

    public void addMessage(MessageDto messageDto){
        messages.add(messageDto);
    }

}
