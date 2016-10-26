package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resourceGroup")
public class ResourceGroup {
	@Id
	@GeneratedValue
	private Long id;
	private String type; // ENUM
	private Long typeId; //id of resourceType (imagine we have Event entity with x id 
						 // to get all concerning resources type = EVENT id = X
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
}
