package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.City;
import ua.peresvit.entity.Club;

import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class ClubFactory implements BaseFactory<Club> {


    @Override
    public Club getFirst() {
        Club res = new Club();
        res.setClubId(1l);
        res.setClubName("Club 1");
        res.setAddress("Kyiv");
        return res;
    }

    @Override
    public Club getSecond() {
        Club res = new Club();
        res.setClubId(2l);
        res.setClubName("Club 2");
        res.setAddress("Odessa");
        return res;
    }

    @Override
    public Club getNew() {
        Club res = new Club();
        res.setClubId(3l);
        res.setClubName("Club 3");
        res.setAddress("Lviv");
        return res;
    }

    @Override
    public List<Club> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
