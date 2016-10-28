package org.bionic.service.impl;

import java.util.List;

import org.bionic.dao.ResourceRepository;
import org.bionic.entity.Resource;
import org.bionic.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class ResourceServiceImpl implements ResourceService{
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
	List<Resource> findByResourceGroupId(Long resourceId){
		return resourceRepository.findByResourceId(resourceId);
	}
}
