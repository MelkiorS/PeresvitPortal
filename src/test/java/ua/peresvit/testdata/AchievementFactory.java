package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.Achievement;
import ua.peresvit.entity.City;

import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class AchievementFactory implements BaseFactory<Achievement> {


    @Override
    public Achievement getFirst() {
        Achievement res = new Achievement();
        res.setAchievementId(1l);
        res.setAchievementName("Result 1");
        res.setDescription("Competition result 1");
        res.setImageURL("");
        return res;
    }

    @Override
    public Achievement getSecond() {
        Achievement res = new Achievement();
        res.setAchievementId(2l);
        res.setAchievementName("Result 2");
        res.setDescription("Competition result 2");
        res.setImageURL("");
        return res;
    }

    @Override
    public Achievement getNew() {
        Achievement res = new Achievement();
        res.setAchievementId(3l);
        res.setAchievementName("Result 3");
        res.setDescription("Competition result 3");
        res.setImageURL("");
        return res;
    }

    @Override
    public List<Achievement> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
