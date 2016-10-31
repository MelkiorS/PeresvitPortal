package org.bionic.controller;

import java.util.List;

import org.bionic.entity.Resource;
import org.bionic.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	
	// create resource
	@RequestMapping(value = "/resource/", method = RequestMethod.POST)
	public ResponseEntity<Void> createResource(@RequestBody Resource resource, @RequestParam("file") MultipartHttpServletRequest file, UriComponentsBuilder ucBuilder) {
		resourceService.save(resource, file);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/resource/{resourceId}").buildAndExpand(resource.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// edit resource
	@RequestMapping(value = "/resource/{resourceId}", method = RequestMethod.PUT)
	public ResponseEntity<Resource> updateResource(@PathVariable("resourceId") long resourceId,
			@RequestBody Resource resource) {
		Resource currentResource = resourceService.findOne(resourceId);
		if (currentResource == null) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Resource>(resourceService.save(resource), HttpStatus.OK);
	}

	// delete resource
	@RequestMapping(value = "/resource/{resourceId}", method = RequestMethod.DELETE)
	public ResponseEntity<Resource> deleteResource(@PathVariable("resourceId") long resourceId) {
		Resource resource = resourceService.findOne(resourceId);
		if (resource == null) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}

		resourceService.delete(resourceService.findOne(resourceId));
		return new ResponseEntity<Resource>(HttpStatus.NO_CONTENT);
	}

	// show resource by id
	@RequestMapping(value = "/resource/{resourceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource> getResource(@PathVariable("resourceId") long resourceId) {
		Resource resource = resourceService.findOne(resourceId);
		if (resource == null) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Resource>(resource, HttpStatus.OK);
	}

	// show all resources
	@RequestMapping(value = "/resource/", method = RequestMethod.GET)
	public ResponseEntity<List<Resource>> listAllResources() {
		List<Resource> resource = resourceService.findAll();
		if (resource.isEmpty()) {
			return new ResponseEntity<List<Resource>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Resource>>(resource, HttpStatus.OK);
	}
			
}
