package org.bionic.dao;

import org.bionic.entity.Rang;
import org.bionic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional()
public interface UserRepository extends JpaRepository<User, Long> {

    <S extends User> S save(S arg0);

    User findByEmail(String email);

    User findOne(Long userId);

    List<User> findAll();

    List<User> findByRang(Rang rang);

    void delete(User arg0);

    boolean equals(Object obj);

    User findUserByEmailAndPassword(String email,String password);
}
