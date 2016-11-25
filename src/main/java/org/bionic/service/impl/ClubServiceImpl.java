package org.bionic.service.impl;

import org.bionic.dao.ClubRepository;
import org.bionic.entity.Club;
import org.bionic.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public <S extends Club> S save(S arg0) { return clubRepository.save(arg0); }
    @Override
    public Club findOne(Long arg0) {
        return clubRepository.findOne(arg0);
    }
    @Override
    public java.util.List<Club> findAll() {return clubRepository.findAll(); }
}
