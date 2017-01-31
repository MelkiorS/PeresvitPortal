package ua.peresvit.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long messageId;
    private String content;
    private Timestamp createdAt;
    private boolean readStatus;
//    Functional message like about creating chat or inviting new members;
    private boolean functional;

    @ManyToOne
    @JoinColumn(name="senderId")
    private User sender;
//    if current message refers to chat Receiver - is null
//    @ManyToOne
//    @JoinColumn(name="receiverId")
//    private User receiver;
    @ManyToOne
    @JoinColumn(name="chatId")
    private Chat chat;
    // setting default value for message as unread
    public Message() {
        this.readStatus = false;
    }
}
