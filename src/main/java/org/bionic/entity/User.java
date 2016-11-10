package org.bionic.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	@Cascade(CascadeType.DELETE)
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
	public User() {}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + /*", login=" + login
				+ */ ", password=" + password + ", email=" + email + "]";
	}
}
