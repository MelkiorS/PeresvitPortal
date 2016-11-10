package org.bionic.dao;

import org.bionic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional()
public interface UserRepository extends JpaRepository<User, Long> {

    <S extends User> S save(S arg0);

    User findByEmail(String email);

    User findOne(Long userId);

    java.util.List<User> findAll();

    void delete(User arg0);

    boolean equals(Object obj);
}
