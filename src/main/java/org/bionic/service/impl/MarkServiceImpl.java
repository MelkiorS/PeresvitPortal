package org.bionic.service.impl;

import org.bionic.dao.MarkRepository;
import org.bionic.entity.Mark;
import org.bionic.service.MarkService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService{

    @Autowired
    private MarkRepository markRepository;

    @Override
    public <S extends Mark> S save(S arg0) { return markRepository.save(arg0); }
    @Override
    public Mark findOne(Long arg0) {

        Mark mark =  markRepository.findOne(arg0);
        //Hibernate.initialize(mark.getUsers());
        return mark;
    }
    @Override
    public java.util.List<Mark> findAll() {return markRepository.findAll(); }
}
