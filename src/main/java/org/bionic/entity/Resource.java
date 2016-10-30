package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
public class Resource {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String description;
	private int type; // ENUM
	private String url;
	@ManyToOne
	@JoinColumn(name="groupId")
	private ResourceGroup group; //ManyToOne 
	@ManyToOne
	@JoinColumn(name="ownerId")
	private User user; // If personal information (info concerning user)
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public ResourceGroup getGroup() {
		return group;
	}

	public void setGroup(ResourceGroup group) {
		this.group = group;
	}
	
}
