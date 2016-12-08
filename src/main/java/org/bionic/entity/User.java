package org.bionic.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	private String profileVK;
	private String profileFB;
	private String profileIS;

	@Column(columnDefinition = "boolean default true")
	private boolean gender;

	@ManyToOne
	@JoinColumn(name="cityId")
	private City city;

	@ManyToOne
	@JoinColumn(name="clubId")
	private Club club;

	@ManyToOne
	@JoinColumn(name="combatArtId")
	private CombatArt combatArt;

	@ManyToOne
	private User mentor;

	private String about;

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

//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
//	private Set<Event> events = new HashSet<Event>();

	public User() {
		super();
		enabled = true;
		gender  = true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		return userId.equals(user.userId);

	}

	@Override
	public int hashCode() {
		return userId.hashCode();
	}

	public void addUserInfo(UserInfo userInfo){
		userInfoList.add(userInfo);
	}

	@Override
	public String toString() {
		return "" + firstName.trim() + " " + lastName.trim();
	}
}
