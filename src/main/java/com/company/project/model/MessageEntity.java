package com.company.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "MESSAGE_T")
@Getter
@Setter
@ToString(exclude = {"sender", "recipient", "conversation"}, callSuper = true)
public class MessageEntity extends AbstractEntity {
    private String body;
    @Column(name = "is_read")
    private boolean isRead = false;
    @Column(name = "post_date")
    private LocalDateTime postDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private UserEntity sender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id")
    private UserEntity recipient;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conversation_id")
    private ConversationEntity conversation;

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof MessageEntity)) return false;
        MessageEntity that = (MessageEntity) o;
        return
                Objects.equals(this.getId(), that.getId()) &&
                        Objects.equals(body, that.body) &&
                        Objects.equals(isRead, that.isRead) &&
                        Objects.equals(postDate, that.postDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, postDate, isRead, this.getId());
    }
}
