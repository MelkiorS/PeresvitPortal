package ua.peresvit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.peresvit.dao.ResourceGroupRepository;
import ua.peresvit.entity.ResourceGroup;
import ua.peresvit.entity.ResourceGroupType;
import ua.peresvit.entity.Role;
import ua.peresvit.service.ResourceGroupService;

@Service
public  class ResourceGroupServiceImpl implements ResourceGroupService {
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
	ResourceGroup findResourceGroupByResourceGroupTypeAndRole(ResourceGroupType type, Role role){
		return resourceGroupRepository.findResourceGroupByResourceGroupTypeAndRole(type,role);
	}
}
