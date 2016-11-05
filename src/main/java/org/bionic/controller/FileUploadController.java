package org.bionic.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.annotation.MultipartConfig;

import org.bionic.config.Constant;
import org.bionic.entity.Resource;
import org.bionic.entity.ResourceGroup;
import org.bionic.entity.User;
import org.bionic.service.ResourceGroupService;
import org.bionic.service.ResourceService;
import org.bionic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin
// Max uploaded file size (here it is 20 MB)
@MultipartConfig(fileSizeThreshold = 1024*1024*20)
public class FileUploadController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ResourceGroupService resourceGroupService;
	
	@RequestMapping(value = "/upload_avatar")
	public ResponseEntity<?> uploadAvatarFile(@RequestParam("userId") Long userId, MultipartHttpServletRequest request) {	
		
		User user = userService.findByUserId(userId);
		
		if(user==null)
			return new ResponseEntity<>("{user with id " + userId + " not found}", HttpStatus.INTERNAL_SERVER_ERROR);
			
        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile inputFile = request.getFile(uploadedFile);

                if (!inputFile.isEmpty()) {
        			try {
        				String originalFilename = inputFile.getOriginalFilename();
        				String pathFile = Constant.UPLOAD_PATH + "/" + Constant.USR_FOLDER + userId + "/" + Constant.AVATAR;
        				File destinationFile = new File(request.getServletContext().getRealPath(pathFile), originalFilename);
        				destinationFile.getParentFile().mkdirs();
        				inputFile.transferTo(destinationFile);
        				
        				// update user URL
        				pathFile = destinationFile.getPath();
        				user.setAvatarURL(pathFile);
        				userService.update(user, userId);
        				
                        // Generate the http headers with the file properties
                        HttpHeaders headers = new HttpHeaders();
                        headers.add("content-disposition", pathFile);
                        headers.add("userId", "" + user.getUserId());
                        
        				return new ResponseEntity<>(headers, HttpStatus.OK);
        			} catch (Exception e) {return new ResponseEntity<>("{saving error}", HttpStatus.INTERNAL_SERVER_ERROR);}
        			
        		}               
            }
        }
        catch (Exception e) {return new ResponseEntity<>("{saving error}", HttpStatus.INTERNAL_SERVER_ERROR);}
        
        return new ResponseEntity<>("file not selected", HttpStatus.INTERNAL_SERVER_ERROR);
	}
      
	@RequestMapping(value = "/upload_resource")
	public ResponseEntity<?> uploadResourceFile(@RequestParam("resourceId") Long resourceId, MultipartHttpServletRequest request) {	

		// define user
		User user = null; Long userId = null;
		if(request.getParameter("userId") != null){
			userId = Long.parseLong(request.getParameter("userId"));
			user = userService.findByUserId(userId);
		}
		
		// define resource Group
		ResourceGroup resourceGroup = null;
		if(request.getParameter("groupId") != null)
			resourceGroup = resourceGroupService.findOne(Long.parseLong(request.getParameter("groupId")));
		
		// if resource not exist instance it
		Resource resource = resourceService.findOne(resourceId);
		boolean itsNewResource = false;
		if(resource==null){
			resource = new Resource(request.getParameter("title"), request.getParameter("description"), user);
			itsNewResource = true;
		}
			
        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile inputFile = request.getFile(uploadedFile);

                if (!inputFile.isEmpty()) {
        			try {
        				String originalFilename = inputFile.getOriginalFilename();
        				
        				String pathFile = "";
        				String fileContentType = inputFile.getContentType();
        				if(userId != null && userId != 0)
        					pathFile = Constant.UPLOAD_PATH + "/" + Constant.USR_FOLDER + userId + "/" + fileContentType;
        				else
        					pathFile = Constant.UPLOAD_PATH + "/" + Constant.USER_UNKNOWN + "/" + fileContentType;
        				
        				File destinationFile = new File(request.getServletContext().getRealPath(pathFile), originalFilename);
        				destinationFile.getParentFile().mkdirs();
        				inputFile.transferTo(destinationFile);
        				
                        // Split the mimeType into primary and sub types
                        String primaryType, subType;
                        try {
                            primaryType = fileContentType.split("/")[0];
                            subType = fileContentType.split("/")[1];
                        }
                            catch (IndexOutOfBoundsException | NullPointerException ex) {
                            return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                        
        				// save or update user
        				pathFile = destinationFile.getPath();
        				// resource.setType(primaryType);
        				resource.setUrl(pathFile);
        				
        				if(itsNewResource)
        					resourceService.save(resource);
        				/*else
        					resourceService.update(resource, resourceId);*/
        				

                        // Generate the http headers with the file properties
        				HttpHeaders headers = new HttpHeaders();
                        headers.add("content-disposition", pathFile);
                        headers.add("resourceId", "" + resource.getResourceId());
                        
        				return new ResponseEntity<>(headers, HttpStatus.OK);
        			} catch (Exception e) {return new ResponseEntity<>("{saving error}", HttpStatus.INTERNAL_SERVER_ERROR);}
        			
        		}               
            }
        }
        catch (Exception e) {return new ResponseEntity<>("{saving error}", HttpStatus.INTERNAL_SERVER_ERROR);}
        
        return new ResponseEntity<>("file not selected", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

