package org.bionic.service.impl;

import java.util.Iterator;
import java.util.List;

import org.bionic.dao.ResourceRepository;
import org.bionic.entity.Resource;
import org.bionic.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public  class ResourceServiceImpl implements ResourceService{
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Override
	public <S extends Resource> S save(S arg0, MultipartHttpServletRequest request){
		
        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
            	
            	S newResourse = (S) new Resource();
            	org.springframework.beans.BeanUtils.copyProperties(arg0, newResourse);
            	
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                
                newResourse.setFileStream(file.getBytes());
                newResourse.setFileType(file.getContentType());
                newResourse.setTitle(file.getOriginalFilename());
         
                return  resourceRepository.save(newResourse);
            }
        }
        catch (Exception e) {
            //return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
		return resourceRepository.save(arg0);
	}
	
	@Override
	public <S extends Resource> S save(S arg0){
		return resourceRepository.save(arg0);
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
	public void delete(Resource arg0){
		resourceRepository.delete(arg0);
	}
	
	@Override
	public boolean equals(Object obj){
		return resourceRepository.equals(obj);
	}
	
	@Override
	public List<Resource> findByResourceGroupId(Long resourceId){
		return resourceRepository.findByResourceId(resourceId);
	}
}
