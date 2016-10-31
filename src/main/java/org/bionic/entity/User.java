package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "user")
public @Data class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
 
    private String fname;
	private String lname;
	private String mname;
	private String login;
    
    @JsonIgnore
    private String pass;
	private String email;
	private String avatarURL;
     
	public User() {}
	
}
