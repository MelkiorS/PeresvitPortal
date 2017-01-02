package ua.peresvit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.peresvit.entity.UserGroup;
import ua.peresvit.dao.UserGroupRepository;
import ua.peresvit.service.UserGroupService;

import java.util.List;

/**
 * Created by maximmaximov_2 on 15.12.16.
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupRepository dao;

    @Override
    public List<UserGroup> findAll() {
        return dao.findAll();
    }

    @Override
    public UserGroup create(UserGroup e) {
        return dao.save(e);
    }

    @Override
    public UserGroup delete(UserGroup e) {
        dao.delete(e);
        return e;
    }

    @Override
    public UserGroup update(UserGroup e) {
        return dao.save(e);
    }

    @Override
    public UserGroup findById(long id) {
        return dao.findOne(id);
    }
}
