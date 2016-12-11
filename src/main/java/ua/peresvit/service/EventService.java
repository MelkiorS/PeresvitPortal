package ua.peresvit.service;



import ua.peresvit.entity.Event;

import java.util.Date;
import java.util.List;


public interface EventService {

    List<Event> findAll();
    Event create(Event e);
    Event delete(Event e);
    Event update(Event e);
    Event findById(long id);

    List<Event> findClosest(Date date, int count);
    Event findNext(Date date);
    List<Event> getPeriod(Date start, Date finish);

    boolean isAssignedToMe(Event e);
    boolean assignToMe(Event e);

}
