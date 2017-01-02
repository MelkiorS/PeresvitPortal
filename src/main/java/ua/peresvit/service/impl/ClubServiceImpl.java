package ua.peresvit.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.peresvit.dao.ClubRepository;
import ua.peresvit.entity.Club;
import ua.peresvit.service.ClubService;

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
