package org.bionic.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data	
@Entity
@Table(name = "resourceGroup")
public class ResourceGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long resourceGroupId;
	// resource content type
	@ManyToOne
	@JoinColumn(name="resourceGroupTypeId")
	private ResourceGroupType resourceGroupType;
	// rang of resource, it means user of which rang can see it
	@ManyToOne
	@JoinColumn(name="rangId")
	private Rang rang;	
	/*ResourceGroupId of resourceType (imagine we have Event entity with x resourceGroupId
	to get all concerning resources type = EVENT resourceGroupId = X*/
	private Long typeId; 
	/*@OneToMany(mappedBy = "resourceGroup")
	Collection<Resource> resourceCollection;*/
    /*@ManyToMany(
            mappedBy="resourceGroups",
            targetEntity = User.class
    )
    private Set<User> users = new HashSet<User>();*/
}
