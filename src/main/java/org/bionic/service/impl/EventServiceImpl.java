package org.bionic.service.impl;

import org.springframework.stereotype.Service;

import org.bionic.dao.EventRepository;
import org.bionic.entity.Event;
import org.bionic.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
