package org.bionic.dao;

import org.bionic.entity.ResourceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.Long;

@Repository
public interface ResourceGroupRepository extends JpaRepository<ResourceGroup, Long> {
	<S extends ResourceGroup> S save(S arg0);

	ResourceGroup findOne(Long arg0);

	java.util.List<ResourceGroup> findAll();

	void delete(ResourceGroup arg0);

	boolean equals(Object obj);

	ResourceGroup findByResourceGroupId(String resourceGroupId);
}
