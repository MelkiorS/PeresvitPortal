package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userInfo")
@JsonIgnoreProperties({"user", "user"})
public class UserInfo {

	@Id
    @GeneratedValue
	@Getter @Setter private Long userInfoId;
	
	@ManyToOne
	@JoinColumn(name="userID")
	@Getter @Setter private User user;

	@Getter @Setter private String InfoName;
	@Getter @Setter private String InfoValue;
	
    public UserInfo() {}
	
	@Override
	public String toString() {
		return "UserInfo [userInfoId=" + userInfoId + ", user=" + user + ", InfoName=" + InfoName + ", InfoValue=" + InfoValue + "]";
	}
    
    
}

