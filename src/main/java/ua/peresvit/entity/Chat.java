package ua.peresvit.entity;

import lombok.Data;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chatId;
    private String chatTitle;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "chatId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<User> members = new HashSet<>();
    @BatchSize(size = 30)
    @OneToMany(mappedBy = "chat")
    private Collection<Message> messages;
//    @OneToOne
//    @JoinColumn(name = "messageId")
//    private Message lastMessage;
//    private Set<User> usersHaveRead = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User owner;
}