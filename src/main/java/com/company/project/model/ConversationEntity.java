package com.company.project.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CONVERSATION_T")
@Getter
@Setter
@ToString(exclude = {"messages"},callSuper = true)
public class ConversationEntity extends AbstractEntity {
    private String title;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "conversation",
               cascade = {CascadeType.ALL},orphanRemoval = true,fetch = FetchType.LAZY)
    @OrderBy("id")
    private Set<MessageEntity> messages = new LinkedHashSet<>();

    public void addMessage(MessageEntity messageEntity){
        this.messages.add(messageEntity);
        messageEntity.setConversation(this);
    }

    public void removeMessage(MessageEntity messageEntity){
        this.messages.remove(messageEntity);
        messageEntity.setConversation(null);
    }

    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if (this == o) return true;
        if (!(o instanceof ConversationEntity)) return false;
        ConversationEntity that = (ConversationEntity) o;
        return
                Objects.equals(this.getId(),that.getId()) &&
                Objects.equals(title, that.title) &&
                Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, creationDate,this.getId());
    }
}
