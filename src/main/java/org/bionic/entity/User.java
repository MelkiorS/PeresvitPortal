package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Long id;
 
    @Getter @Setter private String fname;
	@Getter @Setter private String lname;
	@Getter @Setter private String mname;
	@Getter @Setter private String login;
    
    @JsonIgnore
    @Getter @Setter private String pass;
	@Getter @Setter private String email;
	@Getter @Setter private String avatarURL;
     
	public User() {}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", mname=" + mname + ", login=" + login
				+ ", pass=" + pass + ", email=" + email + "]";
	}
	
}
