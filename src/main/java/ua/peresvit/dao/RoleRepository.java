package ua.peresvit.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.peresvit.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
	<S extends Role> S save(S arg0);

	Role findOne(Long arg0);

	List<Role> findAll();

	void delete(Role arg0);

	boolean equals(Object obj);

	List<Role> findByRangId(Long rangId);
}
