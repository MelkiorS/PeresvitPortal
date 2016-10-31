package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "userinfo")
@JsonIgnoreProperties({"user", "user"})
public @Data class UserInfo {

	@Id
    @GeneratedValue
    private Long id;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	private String InfoName;
	private String InfoValue;
	
    public UserInfo() {}
    
    
}

