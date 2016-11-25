package org.bionic.dao;

import org.bionic.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ClubRepository extends JpaRepository<Club, Long> {

    <S extends Club> S save(S arg0);

    Club findOne(Long arg0);

    java.util.List<Club> findAll();
}
