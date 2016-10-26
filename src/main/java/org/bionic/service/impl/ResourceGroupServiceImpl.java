package org.bionic.service.impl;

import java.util.List;

import org.bionic.dao.ResourceGroupRepository;
import org.bionic.entity.ResourceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public  class ResourceGroupServiceImpl implements ResourceGroupRepository{
	@Autowired
	private ResourceGroupRepository resourceGroupRepository;
	
	@Override
	public
	<S extends ResourceGroup> S save(S arg0){
		return resourceGroupRepository.save(arg0);
	}
	
	@Override
	public
	ResourceGroup findOne(Long arg0){
		return resourceGroupRepository.findOne(arg0);
	}
	
	@Override
	public
	java.util.List<ResourceGroup> findAll(){
		return resourceGroupRepository.findAll();
	}
	
	@Override
	public
	void delete(ResourceGroup arg0){
		resourceGroupRepository.delete(arg0);
	}
	
	@Override
	public
	boolean equals(Object obj){
		return resourceGroupRepository.equals(obj);
	}
	
	@Override
	public
	ResourceGroup findByType(String type){
		return resourceGroupRepository.findByType(type);
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<ResourceGroup> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ResourceGroup> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceGroup> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResourceGroup getOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ResourceGroup> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ResourceGroup> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ResourceGroup> findAll(Pageable arg0) {
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
	public void delete(Iterable<? extends ResourceGroup> arg0) {
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
