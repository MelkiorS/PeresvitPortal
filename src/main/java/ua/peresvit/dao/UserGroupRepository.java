package ua.peresvit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.peresvit.entity.UserGroup;

/**
 * Created by maximmaximov_2 on 15.12.16.
 */
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

}
