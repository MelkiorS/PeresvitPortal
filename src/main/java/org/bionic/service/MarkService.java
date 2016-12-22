package org.bionic.service;

import org.bionic.entity.Mark;

import java.util.List;

public interface MarkService {

    <S extends Mark> S save(S arg0);

    Mark findOne(Long markId);

    List<Mark> findAll();

}
