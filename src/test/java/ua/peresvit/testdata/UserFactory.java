package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.Achievement;
import ua.peresvit.entity.Mark;
import ua.peresvit.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class UserFactory implements BaseFactory<User> {


    @Override
    public User getFirst() {
        User res = new User();
        res.setUserId(1l);
        res.setFirstName("Ivan");
        res.setLastName("Ivanovich");
        res.setMiddleName("Ivanov");
        res.setPassword("1");
        res.setEmail("ivan@gmail.com");
        res.setAvatarURL("av1");
        res.setProfileVK("vk1");
        res.setProfileFB("fb1");
        res.setProfileGoogle("google1");
        res.setProfileInstagram("instagram1");
        res.setSex('m');
        res.setCity(new CityFactory().getFirst());
        res.setClub(new ClubFactory().getFirst());
        res.setCombatArt(new CombatArtFactory().getFirst());
        res.setMentor(null);
        res.setRole(new RoleFactory().getFirst());
        res.setAboutMe("Me1");
        res.setEnabled(true);
        res.setMarks(new HashSet<Mark>());
        res.setAchievements(new HashSet<Achievement>());
        return res;
    }

    @Override
    public User getSecond() {
        User res = new User();
        res.setUserId(2l);
        res.setFirstName("Petr");
        res.setLastName("Petrovich");
        res.setMiddleName("Petrov");
        res.setPassword("2");
        res.setEmail("petr@gmail.com");
        res.setAvatarURL("av2");
        res.setProfileVK("vk2");
        res.setProfileFB("fb2");
        res.setProfileGoogle("google2");
        res.setProfileInstagram("instagram2");
        res.setSex('m');
        res.setCity(new CityFactory().getFirst());
        res.setClub(new ClubFactory().getFirst());
        res.setCombatArt(new CombatArtFactory().getFirst());
        res.setMentor(null);
        res.setRole(new RoleFactory().getFirst());
        res.setAboutMe("Me2");
        res.setEnabled(true);
        res.setMarks(new HashSet<Mark>());
        res.setAchievements(new HashSet<Achievement>());
        return res;
    }

    @Override
    public User getNew() {
        User res = new User();
        res.setUserId(3l);
        res.setFirstName("Sidor");
        res.setLastName("Sidorovich");
        res.setMiddleName("Sidorov");
        res.setPassword("3");
        res.setEmail("sidor@gmail.com");
        res.setAvatarURL("av3");
        res.setProfileVK("vk3");
        res.setProfileFB("fb3");
        res.setProfileGoogle("google3");
        res.setProfileInstagram("instagram3");
        res.setSex('m');
        res.setCity(new CityFactory().getFirst());
        res.setClub(new ClubFactory().getFirst());
        res.setCombatArt(new CombatArtFactory().getFirst());
        res.setMentor(null);
        res.setRole(new RoleFactory().getFirst());
        res.setAboutMe("Me3");
        res.setEnabled(true);
        res.setMarks(new HashSet<Mark>());
        res.setAchievements(new HashSet<Achievement>());
        return res;
    }

    @Override
    public List<User> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
