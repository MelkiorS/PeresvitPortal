package org.bionic.service;

import java.util.List;

import org.bionic.entity.Rang;

public interface RangService {
	<S extends Rang> S save(S arg0);

	Rang findOne(Long arg0);

	java.util.List<Rang> findAll();

	void delete(Rang arg0);

	boolean equals(Object obj);

	List<Rang> findByRangId(Long rangId);
}
