package org.bionic.dao;

import java.util.List;

import org.bionic.entity.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceTypeRepository extends JpaRepository<ResourceType, Long> {
	<S extends ResourceType> S save(S arg0);

	ResourceType findOne(Long arg0);

	java.util.List<ResourceType> findAll();

	void delete(ResourceType arg0);

	boolean equals(Object obj);

	List<ResourceType> findByResourceTypeId(Long resourceTypeId);

}
