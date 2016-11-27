package org.bionic.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.annotations.*;

import java.util.List;

@Entity
@Data
@Table(name = "resourceGroupType")
public class ResourceGroupType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long resourceGroupTypeId;	
	private String groupName; // ManyToOne
	private String caption;
	@OneToMany(mappedBy = "resourceGroupType", fetch = FetchType.EAGER)
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private List<ResourceGroupTypeChapter> chapterList;
}
