package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "userinfo")
@JsonIgnoreProperties({"user", "user"})
public class UserInfo {

	@Id
    @GeneratedValue
    private Long id;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
    private String InfoName;
    private String InfoValue;
	
    public UserInfo() {}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getInfoName() {
		return InfoName;
	}
	public void setInfoName(String infoName) {
		InfoName = infoName;
	}
	public String getInfoValue() {
		return InfoValue;
	}
	public void setInfoValue(String infoValue) {
		InfoValue = infoValue;
	}
	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", user=" + user + ", InfoName=" + InfoName + ", InfoValue=" + InfoValue + "]";
	}
    
    
}

