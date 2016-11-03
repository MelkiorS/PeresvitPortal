package org.bionic.service;

import java.util.List;

import org.bionic.entity.ResourceType;

public interface ResourceTypeService {
	<S extends ResourceType> S save(S arg0);

	ResourceType findOne(Long arg0);

	java.util.List<ResourceType> findAll();

	void delete(ResourceType arg0);

	boolean equals(Object obj);

	List<ResourceType> findByResourceTypeId(Long resourceTypeId);
}
