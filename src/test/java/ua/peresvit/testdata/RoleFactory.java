package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.Role;
import ua.peresvit.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maximmaximov_2 on 2/6/17.
 */
public class RoleFactory implements BaseFactory<Role> {

    public static Role getAdmin() {
        Role r =  new Role();
        r.setRoleId(1);
        r.setRoleName("ADMIN");
        r.setUserCollection(new ArrayList<User>());
        return r;
    }

    public static Role getUser() {
        Role r =  new Role();
        r.setRoleId(2);
        r.setRoleName("User");
        r.setUserCollection(new ArrayList<User>());
        return r;
    }

    @Override
    public Role getFirst() {
        return getAdmin();
    }

    @Override
    public Role getSecond() {
        return getUser();
    }

    @Override
    public Role getNew() {
        return getNewRole();
    }

    @Override
    public List<Role> getAll() {
        return getAllRoles();
    }

    public static List<Role> getAllRoles() {
        return Lists.newArrayList(getAdmin(), getUser());
    }

    public static Role getNewRole() {
        Role r = new Role();
        r.setRoleId(3);
        r.setRoleName("New role");
        r.setUserCollection(new ArrayList<User>());
        return r;
    }

}
