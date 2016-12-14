package ua.peresvit.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long chatId;
    private String chatTitle;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user",
            joinColumns = @JoinColumn(name = "chatId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Collection<User> receivers;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    private Collection<Message> messages;
}