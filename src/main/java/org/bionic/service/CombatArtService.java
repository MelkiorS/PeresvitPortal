package org.bionic.service;

import org.bionic.entity.CombatArt;

public interface CombatArtService {

    <S extends CombatArt> S save(S arg0);

    CombatArt findOne(Long artId);

    java.util.List<CombatArt> findAll();
}
