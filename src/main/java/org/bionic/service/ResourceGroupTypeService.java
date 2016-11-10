package org.bionic.service;

import java.util.List;

import org.bionic.entity.ResourceGroupType;

public interface ResourceGroupTypeService {

	<S extends ResourceGroupType> S save(S arg0);

	ResourceGroupType findOne(Long arg0);

	java.util.List<ResourceGroupType> findAll();

	void delete(ResourceGroupType arg0);

	boolean equals(Object obj);

	List<ResourceGroupType> findByResourceGroupTypeId(Long resourceGroupTypeId);

	ResourceGroupType findResourceGroupTypeByGroupName(String name);
}