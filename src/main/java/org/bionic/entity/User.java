package org.bionic.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public @Data class User {
	@Id
	@GeneratedValue
	private Long userId;
 
    private String fname;
	private String lname;
	private String mname;
	private String login;
    
    @JsonIgnore
    private String pass;
	private String email;
	private String avatarURL;
     
  	@ManyToMany(
    		cascade = {CascadeType.ALL},
			targetEntity = ResourceGroup.class
	)
    @JoinTable(
    		name="userResourceGroup",
            joinColumns={@JoinColumn(name="userId")},
            inverseJoinColumns={@JoinColumn(name="resourceGroupId")}
	)
    private Set<ResourceGroup> resourceGroups = new HashSet<ResourceGroup>();
         
	public User() {}
	
}
