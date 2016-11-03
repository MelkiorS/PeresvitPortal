package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "resource")
public class Resource {
	@Id
	@GeneratedValue
	private Long resourceId;
	private String title;
	private String description;
	private int type; 					// ENUM
	private String url;
	@ManyToOne
	@JoinColumn(name="resourceGroupId")
	private ResourceGroup group; 		//ManyToOne 
	@ManyToOne
	@JoinColumn(name="ownerId")
	private User user; 					// If personal information (info concerning user)

	public Resource(){}
	
	public Resource(String title, String description, int type, ResourceGroup group, User user) {
		this.title = title;
		this.description = description;
		this.type = type;
		this.group = group;
		this.user = user;
	}
	
	
}
