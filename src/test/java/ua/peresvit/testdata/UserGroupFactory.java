package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.User;
import ua.peresvit.entity.UserGroup;

import java.util.HashSet;
import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class UserGroupFactory implements BaseFactory<UserGroup> {


    @Override
    public UserGroup getFirst() {
        UserGroup res = new UserGroup();
        res.setId(1l);
        res.setName("Group1");
        res.setUsers(new HashSet<User>());
        return res;
    }

    @Override
    public UserGroup getSecond() {
        UserGroup res = new UserGroup();
        res.setId(2l);
        res.setName("Group2");
        res.setUsers(new HashSet<User>());
        return res;
    }

    @Override
    public UserGroup getNew() {
        UserGroup res = new UserGroup();
        res.setId(3l);
        res.setName("Group3");
        res.setUsers(new HashSet<User>());
        return res;
    }

    @Override
    public List<UserGroup> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
