package org.bionic.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import org.bionic.web.dto.UserDto;

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

    @JsonIgnore
    private String password;
	private String email;
	private String avatarURL;
	// level of user
	@ManyToOne
	@JoinColumn(name="rangId")
	private Rang rang;
    //Account verification status
    private boolean enabled;
  /*  @ManyToMany(
    		cascade = {CascadeType.ALL},
			targetEntity = ResourceGroup.class
	)
    @JoinTable(
    		name="userResourceGroup",
            joinColumns={@JoinColumn(name="userId")},
            inverseJoinColumns={@JoinColumn(name="resourceGroupId")}
	)
    private Set<ResourceGroup> resourceGroups = new HashSet<ResourceGroup>();
     */
	public User() {
        super();
        this.enabled = false;
    }

    public User(UserDto accountDto){
        super();
        firstName = accountDto.getFirstName();
        lastName = accountDto.getLastName();
        middleName = accountDto.getMiddleName();
        password = accountDto.getPassword();
        email = accountDto.getEmail();
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + /*", login=" + login
				+ */ ", password=" + password + ", email=" + email + "]";
	}
}
