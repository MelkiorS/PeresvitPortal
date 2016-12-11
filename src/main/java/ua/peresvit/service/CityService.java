package ua.peresvit.service;


import ua.peresvit.entity.City;

public interface CityService {

    <S extends City> S save(S arg0);

    City findOne(Long cityId);

    java.util.List<City> findAll();

}
