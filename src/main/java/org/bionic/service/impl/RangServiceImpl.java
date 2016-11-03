package org.bionic.service.impl;

import java.util.List;

import org.bionic.dao.RangRepository;
import org.bionic.entity.Rang;
import org.bionic.service.RangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RangServiceImpl implements RangService {
	@Autowired
	private RangRepository rangRepository;
	
	@Override
	public
	<S extends Rang> S save(S arg0){
		return rangRepository.save(arg0);
	}
	
	@Override
	public
	Rang findOne(Long arg0){
		return rangRepository.findOne(arg0);
	}
	
	@Override
	public
	java.util.List<Rang> findAll(){
		return rangRepository.findAll();
	}
	
	@Override
	public
	void delete(Rang arg0){
		rangRepository.delete(arg0);
	}
	
	@Override
	public
	boolean equals(Object obj){
		return rangRepository.equals(obj);
	}

	@Override
	public List<Rang> findByRangId(Long rangId) {
		return rangRepository.findByRangId(rangId);
	}
}
