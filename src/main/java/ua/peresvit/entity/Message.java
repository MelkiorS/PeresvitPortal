package ua.peresvit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long messageId;
    private String content;
    private long chapterId;
    private boolean read;

    @ManyToOne
    @JoinColumn(name="senderId")
    private User author;
//    // if current message refers to chat Receiver - is null
    @ManyToOne
    @JoinColumn(name="receiverId")
    private User receiver;
    @ManyToOne
    @JoinColumn(name="chatId")
    private Chat chat;
    // setting default value for message as unread
    public Message() {
        this.read = false;
    }
}
