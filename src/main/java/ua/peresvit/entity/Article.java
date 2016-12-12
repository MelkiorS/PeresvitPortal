package ua.peresvit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long articleId;
    private String articleName;
    @Lob
    private String context;
    private long chapterId;
    // resource content type
    @ManyToOne
    @JoinColumn(name="resourceGroupTypeId")
    private ResourceGroupType resourceGroupType;
    // resource group type block

    // role of resource, it means user of which role can see it
    @ManyToOne
    @JoinColumn(name="roleId")
    private Role role;
    // @OneToMany(mappedBy = "role")
  //  @LazyCollection(LazyCollectionOption.FALSE)
   // private Collection<User> userCollection;
}
