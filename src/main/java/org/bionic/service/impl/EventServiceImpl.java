package org.bionic.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.bionic.dao.EventRepository;
import org.bionic.entity.Event;
import org.bionic.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Event by MMaximov 03.11.2016
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository dao;

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
}
