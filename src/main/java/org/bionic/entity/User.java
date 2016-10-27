package org.bionic.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Long userId;
 
    @Getter @Setter private String fname;
	@Getter @Setter private String lname;
	@Getter @Setter private String mname;
	@Getter @Setter private String login;
    
    @JsonIgnore
    @Getter @Setter private String pass;
	@Getter @Setter private String email;
	@Getter @Setter private String avatarURL;
    @ManyToMany(
    		cascade = {CascadeType.ALL},
			targetEntity = ResourceGroup.class
	)
    @JoinTable(
    		name="User_ResourceGroup",
            joinColumns={@JoinColumn(name="UserID")},
            inverseJoinColumns={@JoinColumn(name="ResourceGroupID")}
	)
    @Getter @Setter private Set<ResourceGroup> resourceGroups = new HashSet<ResourceGroup>();
     
	public User() {}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", mname=" + mname + ", login=" + login
				+ ", pass=" + pass + ", email=" + email + "]";
	}
}
