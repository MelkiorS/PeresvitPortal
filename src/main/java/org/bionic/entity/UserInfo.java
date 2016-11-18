package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "userInfo")
@JsonIgnoreProperties({"user", "user"})
public class UserInfo {

	@Id
    @GeneratedValue
	private Long userInfoId;
	
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	@JoinColumn(name="userId")
	private User user;

	private String infoName;
	private String infoValue;
	
    public UserInfo() {}

	public UserInfo(String infoName, String infoValue) {
		this.infoName = infoName;
		this.infoValue = infoValue;
	}
  
    
	
}

