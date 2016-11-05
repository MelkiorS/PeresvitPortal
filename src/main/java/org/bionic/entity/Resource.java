package org.bionic.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "resource")
public class Resource {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long resourceId;
	private String title;
	private String description;
	private String url;
	// resource format type
	@ManyToOne
	@JoinColumn(name="resourceTypeId")
	private ResourceType resourceType;
	// resource group 
	@ManyToOne
	@JoinColumn(name="resourceGroupId")
	private ResourceGroup resourceGroup;
	// If personal information (info concerning user)
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	public Resource(){};
	
	public Resource(String title, String description, ResourceType type, ResourceGroup group, User user) {
		this.title = title;
		this.description = description;
		this.resourceType = type;
		this.resourceGroup = group;
		this.user = user;
	}
	
	public Resource(String title, String description, User user) {
		this.title = title;
		this.description = description;
		this.user = user;
	}
}
