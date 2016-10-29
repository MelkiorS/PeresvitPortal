package org.bionic.service;

import java.util.List;

import org.bionic.entity.Resource;




public interface ResourceService{
	
	<S extends Resource> S save(S arg0);

	Resource findOne(Long arg0);

	java.util.List<Resource> findAll();

	void delete(Resource arg0);

	boolean equals(Object obj);

	List<Resource> findByResourceGroupId(Long resourceGroupId);
}
