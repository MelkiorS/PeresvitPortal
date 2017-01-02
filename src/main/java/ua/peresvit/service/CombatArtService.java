package ua.peresvit.service;


import ua.peresvit.entity.CombatArt;

public interface CombatArtService {

    <S extends CombatArt> S save(S arg0);

    CombatArt findOne(Long artId);

    java.util.List<CombatArt> findAll();
}
