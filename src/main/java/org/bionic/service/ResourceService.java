package org.bionic.service;

import java.util.List;

import org.bionic.entity.Resource;
import org.springframework.transaction.annotation.Transactional;

public interface ResourceService{
	
	@Transactional
	<S extends Resource> S save(S arg0);

	Resource findOne(Long arg0);

	java.util.List<Resource> findAll();

	@Transactional
	void delete(Resource arg0);

	boolean equals(Object obj);

	List<Resource> findByResourceGroupId(Long resourceGroupId);
	
	@Transactional
	Resource update(Resource resource, Long resourceId);
}
