package org.bionic.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "resourceGroup")
public class ResourceGroup {
	@Id
	@GeneratedValue
	@Getter @Setter private Long resourceGroupId;
	@Getter @Setter private String type; // ENUM
	@Getter @Setter private Long typeId; //resourceGroupId of resourceType (imagine we have Event entity with x resourceGroupId
			// to get all concerning resources type = EVENT resourceGroupId = X
	@OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
	@Getter @Setter Collection<Resource> resourceCollection;
    @ManyToMany(
            mappedBy="resourceGroups",
            targetEntity = User.class
    )
    @Getter @Setter private Set<User> users = new HashSet<User>();
}
