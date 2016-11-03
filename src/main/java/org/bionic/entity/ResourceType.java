package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "resourceType")
public class ResourceType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long resourceTypeId;	
	private String typeName;
}
