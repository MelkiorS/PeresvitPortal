package org.bionic.service.impl;

import java.util.List;

import org.bionic.dao.ResourceGroupTypeRepository;
import org.bionic.entity.ResourceGroupType;
import org.bionic.service.ResourceGroupTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceGroupTypeServiceImpl implements ResourceGroupTypeService {
	@Autowired
	private ResourceGroupTypeRepository resourceGroupTypeRepository;
	
	@Override
	public
	<S extends ResourceGroupType> S save(S arg0){
		return resourceGroupTypeRepository.save(arg0);
	}
	
	@Override
	public
	ResourceGroupType findOne(Long arg0){
		return resourceGroupTypeRepository.findOne(arg0);
	}
	
	@Override
	public
	java.util.List<ResourceGroupType> findAll(){
		return resourceGroupTypeRepository.findAll();
	}
	
	@Override
	public
	void delete(ResourceGroupType arg0){
		resourceGroupTypeRepository.delete(arg0);
	}
	
	@Override
	public
	boolean equals(Object obj){
		return resourceGroupTypeRepository.equals(obj);
	}

	@Override
	public List<ResourceGroupType> findByResourceGroupTypeId(Long resourceGroupTypeId) {
		return resourceGroupTypeRepository.findByResourceGroupTypeId(resourceGroupTypeId);
	}
}