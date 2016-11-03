package org.bionic.service.impl;

import java.util.List;

import org.bionic.config.Constant;
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
	public Resource save(Resource resource){
		return resourceRepository.save(resource);
	}
	
	@Override
	public Resource findOne(Long arg0){
		return resourceRepository.findOne(arg0);
	}
	
	@Override
	public java.util.List<Resource> findAll(){
		return resourceRepository.findAll();
	}
	
	@Override
	public void delete(Resource resource){
		String pathFile = resource.getUrl();
		resourceRepository.delete(resource);
		Constant.deleteFile(pathFile);
	}
	
	@Override
	public boolean equals(Object obj){
		return resourceRepository.equals(obj);
	}
	
	@Override
	public List<Resource> findByResourceGroupId(Long resourceId){
		return resourceRepository.findByResourceId(resourceId);
	}
	
	@Override
	public Resource update(Resource resource, Long resourceId) {
		
		Resource updatedResource = resourceRepository.findOne(resourceId);
		
		// delete old picture
		String oldFile = updatedResource.getUrl();
		if( oldFile != null && !oldFile.equals(resource.getUrl()))
			Constant.deleteFile(oldFile);
			
		org.springframework.beans.BeanUtils.copyProperties(resource, updatedResource);
		return resourceRepository.save(updatedResource);
	
	}
	
	
}
