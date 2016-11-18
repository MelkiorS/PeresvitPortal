package org.bionic.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
 
    private String firstName;
	private String lastName;
	private String middleName;

    private String password;
	private String email;
	private String avatarURL;
	// level of user
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	@JoinColumn(name="rangId")
	private Rang rang;
    // Account verification status
	@Column(columnDefinition = "boolean default true")
    private boolean enabled = true;

	@OneToMany(mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.DELETE)	
	private List<UserInfo> userInfoList;
	
	public User() {
		super();
		enabled = true;
	}

	public void addUserInfo(UserInfo userInfo){
		userInfoList.add(userInfo);
	}
}
