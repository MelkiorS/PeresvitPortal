package ua.peresvit.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.peresvit.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {

    <S extends City> S save(S arg0);

    City findOne(Long arg0);

    java.util.List<City> findAll();
}
