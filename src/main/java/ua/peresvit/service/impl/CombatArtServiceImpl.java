package ua.peresvit.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.peresvit.dao.CombatArtReppository;
import ua.peresvit.entity.CombatArt;
import ua.peresvit.service.CombatArtService;

@Service
public class CombatArtServiceImpl implements CombatArtService {

    @Autowired
    private CombatArtReppository combatArtReppository;

    @Override
    public <S extends CombatArt> S save(S arg0) { return combatArtReppository.save(arg0); }
    @Override
    public CombatArt findOne(Long arg0) {
        return combatArtReppository.findOne(arg0);
    }
    @Override
    public java.util.List<CombatArt> findAll() {return combatArtReppository.findAll(); }
}
