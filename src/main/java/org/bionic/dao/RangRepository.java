package org.bionic.dao;

import java.util.List;

import org.bionic.entity.Rang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RangRepository extends JpaRepository<Rang, Long> {
	<S extends Rang> S save(S arg0);

	Rang findOne(Long arg0);

	java.util.List<Rang> findAll();

	void delete(Rang arg0);

	boolean equals(Object obj);

	List<Rang> findByRangId(Long rangId);
}
