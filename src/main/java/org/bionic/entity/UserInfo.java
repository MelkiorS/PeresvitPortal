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
@Data
@Table(name = "userInfo")
@JsonIgnoreProperties({"user", "user"})
public class UserInfo {

	@Id
    @GeneratedValue
	private Long userInfoId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	private String InfoName;
	private String InfoValue;
	
    public UserInfo() {}
	
	@Override
	public String toString() {
		return "UserInfo [userInfoId=" + userInfoId + ", user=" + user + ", InfoName=" + InfoName + ", InfoValue=" + InfoValue + "]";
	}
}

