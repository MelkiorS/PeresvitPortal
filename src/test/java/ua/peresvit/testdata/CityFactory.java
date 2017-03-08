package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class CityFactory implements BaseFactory<City> {


    @Override
    public City getFirst() {
        City res = new City();
        res.setCityId(1l);
        res.setCityName("Kyiv");
        return res;
    }

    @Override
    public City getSecond() {
        City res = new City();
        res.setCityId(2l);
        res.setCityName("Odessa");
        return res;
    }

    @Override
    public City getNew() {
        City res = new City();
        res.setCityId(3l);
        res.setCityName("Lviv");
        return res;
    }

    @Override
    public List<City> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
