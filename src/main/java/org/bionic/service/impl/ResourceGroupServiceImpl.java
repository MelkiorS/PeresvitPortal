package org.bionic.service.impl;

import org.bionic.dao.ResourceGroupRepository;
import org.bionic.entity.ResourceGroup;
import org.bionic.service.ResourceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class ResourceGroupServiceImpl implements ResourceGroupService{
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
}
