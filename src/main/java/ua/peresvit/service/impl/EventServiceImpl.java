package ua.peresvit.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.peresvit.dao.EventRepository;
import ua.peresvit.entity.Event;
import ua.peresvit.service.EventService;
import ua.peresvit.service.UserService;

import java.util.Date;
import java.util.List;


@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository dao;

    @Autowired
    private UserService us;

    @Override
    public List<Event> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public Event create(Event e) {
        return dao.save(e);
    }

    @Override
    @Transactional
    public Event delete(Event e) {
        dao.delete(e);
        return e;
    }

    @Override
    @Transactional
    public Event update(Event e) {
        return dao.save(e);
    }

    @Override
    public Event findById(long id) {
        return dao.findOne(id);
    }

    @Override
    public List<Event> findClosest(Date date, int count) {
        Pageable pg = new PageRequest(0,count);
        return dao.getClosest(date, pg);
    }

    @Override
    public Event findNext(Date date) {
        List<Event> lst = findClosest(date, 1);
        return (lst.size()==0) ? null : lst.get(0);
    }

    @Override
    public List<Event> getPeriod(Date start, Date finish) {
        return dao.getPeriod(start, finish);
    }

    @Override
    @Transactional
    public boolean isAssignedToMe(Event e) {
        Event ev = dao.findOne(e.getId());
        return ev.getUsers().contains(us.getCurrentUser());
    }

    @Override
    @Transactional
    public boolean assignToMe(Event e) {
        if (!isAssignedToMe(e)) {
            Event ev = dao.findOne(e.getId());
            ev.getUsers().add(us.getCurrentUser());
            try {
                dao.save(ev);
                return true;
            }
            catch (Exception excp) {
                return false;
            }
        }
        else {
            return true;
        }
    }
}
