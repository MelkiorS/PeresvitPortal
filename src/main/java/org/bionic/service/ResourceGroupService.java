package org.bionic.service;



import org.bionic.entity.ResourceGroup;


public interface ResourceGroupService {

	<S extends ResourceGroup> S save(S arg0);

	ResourceGroup findOne(Long arg0);

	java.util.List<ResourceGroup> findAll();

	void delete(ResourceGroup arg0);

	boolean equals(Object obj);
}
