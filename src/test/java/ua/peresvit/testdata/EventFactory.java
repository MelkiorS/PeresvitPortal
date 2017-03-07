package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.City;
import ua.peresvit.entity.Event;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class EventFactory implements BaseFactory<Event> {


    @Override
    public Event getFirst() {
        Event res = new Event();
        res.setId(1l);
        res.setName("Event 1");
        res.setStart(Timestamp.valueOf("2017-01-01 00:00:00"));
        res.setFinish(Timestamp.valueOf("2017-02-01 00:00:00"));
        res.setCreated(Timestamp.valueOf("2017-01-01 00:00:00"));
        res.setEventUrl("");
        res.setDescription("");
        res.setPlace("");
        res.setConnectAll(false);
        return res;
    }

    @Override
    public Event getSecond() {
        Event res = new Event();
        res.setId(2l);
        res.setName("Event 2");
        res.setStart(Timestamp.valueOf("2017-02-01 00:00:00"));
        res.setFinish(Timestamp.valueOf("2017-03-01 00:00:00"));
        res.setCreated(Timestamp.valueOf("2017-02-01 00:00:00"));
        res.setEventUrl("");
        res.setDescription("");
        res.setPlace("");
        res.setConnectAll(true);
        return res;
    }

    @Override
    public Event getNew() {
        Event res = new Event();
        res.setId(3l);
        res.setName("Event 3");
        res.setStart(Timestamp.valueOf("2017-03-01 00:00:00"));
        res.setFinish(Timestamp.valueOf("2017-04-01 00:00:00"));
        res.setCreated(Timestamp.valueOf("2017-03-01 00:00:00"));
        res.setEventUrl("");
        res.setDescription("");
        res.setPlace("");
        res.setConnectAll(false);
        return res;
    }

    @Override
    public List<Event> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
