package org.bionic.service.impl;

import java.util.List;

import org.bionic.dao.ResourceRepository;
import org.bionic.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public  class ResourceServiceImpl implements ResourceRepository{
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Override
	public
	<S extends Resource> S save(S arg0){
		return resourceRepository.save(arg0);
	}
	
	@Override
	public
	Resource findOne(Long arg0){
		return resourceRepository.findOne(arg0);
	}
	
	@Override
	public
	java.util.List<Resource> findAll(){
		return resourceRepository.findAll();
	}
	
	@Override
	public
	void delete(Resource arg0){
		resourceRepository.delete(arg0);
	}
	
	@Override
	public
	boolean equals(Object obj){
		return resourceRepository.equals(obj);
	}
	
	@Override
	public
	List<Resource> findByGroupId(Long groupid){
		return resourceRepository.findByGroupId(groupid);
	}
	
	@Override
	public
	List<Resource> findByOwnerId(Long ownerid){
		return resourceRepository.findByOwnerId(ownerid);
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<Resource> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Resource> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Resource getOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Resource> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Resource> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Resource> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Resource> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
