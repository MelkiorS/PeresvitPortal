package ua.peresvit.service;

import ua.peresvit.entity.UserGroup;

import java.util.List;

/**
 * Created by maximmaximov_2 on 15.12.16.
 */
public interface UserGroupService {

    List<UserGroup> findAll();
    UserGroup create(UserGroup e);
    UserGroup delete(UserGroup e);
    UserGroup update(UserGroup e);
    UserGroup findById(long id);

}
