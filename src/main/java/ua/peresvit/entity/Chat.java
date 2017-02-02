package ua.peresvit.entity;

import lombok.Data;

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
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    private Collection<Message> messages;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User owner;
}