package org.bionic.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
}
