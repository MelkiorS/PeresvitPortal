package ua.peresvit.dao;


import org.springframework.data.jpa.repository.JpaRepository;


import ua.peresvit.entity.Role;
import ua.peresvit.entity.User;

import java.util.List;



public interface UserRepository extends JpaRepository<User, Long> {

    <S extends User> S save(S arg0);

    User findByEmail(String email);

    User findOne(Long userId);

    List<User> findAll();

    List<User> findByRole(Role role);

    void delete(User arg0);

    boolean equals(Object obj);

    User findUserByEmailAndPassword(String email, String password);
}
