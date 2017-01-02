package ua.peresvit.service;


import ua.peresvit.entity.ResourceGroup;
import ua.peresvit.entity.ResourceGroupType;
import ua.peresvit.entity.Role;

public interface ResourceGroupService {

	<S extends ResourceGroup> S save(S arg0);

	ResourceGroup findOne(Long arg0);

	java.util.List<ResourceGroup> findAll();

	void delete(ResourceGroup arg0);

	boolean equals(Object obj);

	ResourceGroup findResourceGroupByResourceGroupTypeAndRole(ResourceGroupType type, Role role);
}
