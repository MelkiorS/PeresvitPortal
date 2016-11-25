package org.bionic.dao;

import org.bionic.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Long> {

    <S extends City> S save(S arg0);

    City findOne(Long arg0);

    java.util.List<City> findAll();
}
