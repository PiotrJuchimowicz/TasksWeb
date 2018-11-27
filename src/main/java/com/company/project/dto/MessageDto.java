package com.company.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(callSuper = true)
@Getter
@Setter
public class MessageDto extends AbstractDto {
    String body;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH-mm-ss")
    LocalDateTime postDate;
    //in json property name is: read
    private boolean isRead;
    private Long senderId;
    private String recipientEmail;
    private Long conversationId;

}
