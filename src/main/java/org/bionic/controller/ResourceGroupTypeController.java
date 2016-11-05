package org.bionic.controller;

import java.util.List;

import org.bionic.entity.ResourceGroupType;
import org.bionic.service.ResourceGroupTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@CrossOrigin
public class ResourceGroupTypeController {
	@Autowired
	private ResourceGroupTypeService resourceGroupTypeService;
	
	    // create resourceGroupType
		@RequestMapping(value = "/resourceGroupType/", method = RequestMethod.POST)
		public ResponseEntity<Void> createResourceGroupType(@RequestBody ResourceGroupType resourceGroupType, UriComponentsBuilder ucBuilder) {
			resourceGroupTypeService.save(resourceGroupType);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/resourceGroupType/{resourceGroupTypeId}").buildAndExpand(resourceGroupType.getResourceGroupTypeId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		// edit resourceGroupType
		@RequestMapping(value = "/resourceGroupType/{resourceGroupTypeId}", method = RequestMethod.PUT)
		public ResponseEntity<ResourceGroupType> updateResourceGroupType(@PathVariable("resourceGroupTypeId") long resourceGroupTypeId, @RequestBody ResourceGroupType resourceGroupType) {
			ResourceGroupType currentResourceGroupType = resourceGroupTypeService.findOne(resourceGroupTypeId);
			if (currentResourceGroupType == null) {
				return new ResponseEntity<ResourceGroupType>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ResourceGroupType>(resourceGroupTypeService.save(resourceGroupType), HttpStatus.OK);
		}

		// delete resourceGroupType
		@RequestMapping(value = "/resourceGroupType/{resourceGroupTypeId}", method = RequestMethod.DELETE)
	    public ResponseEntity<ResourceGroupType> deleteResourceGroupType(@PathVariable("resourceGroupTypeId") long resourceGroupTypeId) {
	        ResourceGroupType resourceGroupType = resourceGroupTypeService.findOne(resourceGroupTypeId);
	        if (resourceGroupType == null) {
	            return new ResponseEntity<ResourceGroupType>(HttpStatus.NOT_FOUND);
	        }

	        resourceGroupTypeService.delete(resourceGroupTypeService.findOne(resourceGroupTypeId));
	        return new ResponseEntity<ResourceGroupType>(HttpStatus.NO_CONTENT);
	    }

		// show resourceGroupType by id
		@RequestMapping(value = "/resourceGroupType/{resourceGroupTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ResourceGroupType> getResourceGroupType(@PathVariable("resourceGroupTypeId") long resourceGroupTypeId) {
			ResourceGroupType resourceGroupType = resourceGroupTypeService.findOne(resourceGroupTypeId);
			if (resourceGroupType == null) {
			}
			return new ResponseEntity<ResourceGroupType>(resourceGroupType, HttpStatus.OK);
		}

		// show all resourceGroupTypes
		@RequestMapping(value = "/resourceGroupType/", method = RequestMethod.GET)
		public ResponseEntity<List<ResourceGroupType>> listAllResourceGroupTypes() {
			List<ResourceGroupType> resourceGroupType = resourceGroupTypeService.findAll();
			if (resourceGroupType.isEmpty()) {
				return new ResponseEntity<List<ResourceGroupType>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<ResourceGroupType>>(resourceGroupType, HttpStatus.OK);
		}
}