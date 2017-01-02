package ua.peresvit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long achievementId;
    private String achievementName;
    private String description;
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
