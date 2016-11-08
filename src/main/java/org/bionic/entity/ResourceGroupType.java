package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "resourceGroupType")
public class ResourceGroupType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long resourceGroupTypeId;	
	private String groupName; // ManyToOne
}
