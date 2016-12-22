package ua.peresvit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "mark")
public class Mark {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long markId;
    private String markName;
    private String about;
    private String imageURL;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "marks")
//    private Set<User> users = new HashSet<>();
}
