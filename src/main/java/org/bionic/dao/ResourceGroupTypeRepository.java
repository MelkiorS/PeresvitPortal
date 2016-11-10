package org.bionic.dao;

import java.util.List;

import org.bionic.entity.ResourceGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceGroupTypeRepository extends JpaRepository<ResourceGroupType, Long> {
	<S extends ResourceGroupType> S save(S arg0);

	ResourceGroupType findOne(Long arg0);

	java.util.List<ResourceGroupType> findAll();

	void delete(ResourceGroupType arg0);

	boolean equals(Object obj);

	List<ResourceGroupType> findByResourceGroupTypeId(Long resourceGroupTypeId);

	ResourceGroupType findResourceGroupTypeByGroupName(String name);
}
