package org.bionic.dao;

import org.bionic.entity.CombatArt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CombatArtReppository extends JpaRepository<CombatArt, Long> {

    <S extends CombatArt> S save(S arg0);

    CombatArt findOne(Long arg0);

    java.util.List<CombatArt> findAll();
}
