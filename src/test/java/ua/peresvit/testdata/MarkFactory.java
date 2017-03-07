package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.Mark;

import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class MarkFactory implements BaseFactory<Mark> {


    @Override
    public Mark getFirst() {
        Mark res = new Mark();
        res.setMarkId(1l);
        res.setMarkName("Mark 1");
        res.setAbout("About mark 1");
        res.setImageURL("");
        return res;
    }

    @Override
    public Mark getSecond() {
        Mark res = new Mark();
        res.setMarkId(2l);
        res.setMarkName("Mark 2");
        res.setAbout("About mark 2");
        res.setImageURL("");
        return res;
    }

    @Override
    public Mark getNew() {
        Mark res = new Mark();
        res.setMarkId(3l);
        res.setMarkName("Mark 3");
        res.setAbout("About mark 3");
        res.setImageURL("");
        return res;
    }

    @Override
    public List<Mark> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
