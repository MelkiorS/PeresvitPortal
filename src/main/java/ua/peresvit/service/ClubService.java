package ua.peresvit.service;

import ua.peresvit.entity.Club;

public interface ClubService {

    <S extends Club> S save(S arg0);

    Club findOne(Long clubId);

    java.util.List<Club> findAll();
}
