package org.bionic.dao;

import org.bionic.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.Long;
import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
	<S extends Resource> S save(S arg0);

	Resource findOne(Long arg0);

	java.util.List<Resource> findAll();

	void delete(Resource arg0);

	boolean equals(Object obj);

	List<Resource> findByResourceId(Long resourceId);
}
