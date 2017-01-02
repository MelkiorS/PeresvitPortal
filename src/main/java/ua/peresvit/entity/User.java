package ua.peresvit.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
	private String profileGoogle;
	private String profileInstagram;
	private Character sex;

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

	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;

	private String aboutMe;

	// Account verification status
	@Column(columnDefinition = "boolean default true")
	private boolean enabled = true;

	@ManyToMany(
			fetch = FetchType.EAGER,
			targetEntity = Mark.class
	)
	@JoinTable(
			name="user_mark",
			joinColumns={@JoinColumn(name="userId")},
			inverseJoinColumns={@JoinColumn(name="markId")}
	)
	private Set<Mark> marks = new HashSet<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Achievement> achievements = new HashSet<>();

	@Override
	public String toString() {
		return "" + firstName.trim() + " " + lastName.trim();
	}

	public User() {

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

}
