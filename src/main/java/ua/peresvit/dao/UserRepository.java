package ua.peresvit.dao;


import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.peresvit.entity.Role;
import ua.peresvit.entity.User;
import ua.peresvit.entity.UserGroup;

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

    @Query("select distinct u from UserGroup ug INNER JOIN ug.users u where ug in :uglist order by u.role.id desc")
    List<User> getGroupsUsers(@Param("uglist") UserGroup[] ug);

    @Query("select distinct ug from UserGroup ug INNER JOIN ug.users u where u = :user")
    List<UserGroup> getUserGroups(@Param("user") User user);
}
