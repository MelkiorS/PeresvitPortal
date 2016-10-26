package org.bionic.dao;

import org.bionic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional()
public interface UserRepository extends JpaRepository<User, Long> {

}
