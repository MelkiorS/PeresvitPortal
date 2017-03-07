package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.City;
import ua.peresvit.entity.CombatArt;

import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class CombatArtFactory implements BaseFactory<CombatArt> {


    @Override
    public CombatArt getFirst() {
        CombatArt res = new CombatArt();
        res.setCombatArtId(1l);
        res.setCombatArtName("Art 1");
        return res;
    }

    @Override
    public CombatArt getSecond() {
        CombatArt res = new CombatArt();
        res.setCombatArtId(2l);
        res.setCombatArtName("Art 2");
        return res;
    }

    @Override
    public CombatArt getNew() {
        CombatArt res = new CombatArt();
        res.setCombatArtId(3l);
        res.setCombatArtName("Art 3");
        return res;
    }

    @Override
    public List<CombatArt> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
