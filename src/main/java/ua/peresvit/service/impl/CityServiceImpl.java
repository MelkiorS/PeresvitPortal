package ua.peresvit.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.peresvit.dao.CityRepository;
import ua.peresvit.entity.City;
import ua.peresvit.service.CityService;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public <S extends City> S save(S arg0) { return cityRepository.save(arg0); }
    @Override
    public City findOne(Long arg0) {return cityRepository.findOne(arg0);   }
    @Override
    public java.util.List<City> findAll() {return cityRepository.findAll(); }
}
