package org.bionic.service.impl;

import org.bionic.dao.CityRepository;
import org.bionic.entity.City;
import org.bionic.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public <S extends City> S save(S arg0) { return cityRepository.save(arg0); }
    @Override
    public City findOne(Long arg0) {return cityRepository.findOne(arg0);   }
    @Override
    public java.util.List<City> findAll() {return cityRepository.findAll(); }
}
