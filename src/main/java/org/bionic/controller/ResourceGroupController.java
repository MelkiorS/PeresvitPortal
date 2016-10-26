package org.bionic.controller;

import java.util.List;

import org.bionic.entity.ResourceGroup;
import org.bionic.service.impl.ResourceGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ResourceGroupController {
	@Autowired
	private ResourceGroupServiceImpl resourceGroupService;
	
	// create resourceGroup
		@RequestMapping(value = "/resourceGroup/", method = RequestMethod.POST)
		public ResponseEntity<Void> createResourceGroup(@RequestBody ResourceGroup resourceGroup, UriComponentsBuilder ucBuilder) {
			resourceGroupService.save(resourceGroup);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/resourceGroup/{resourceGroupId}").buildAndExpand(resourceGroup.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		// edit resourceGroup
		@RequestMapping(value = "/resourceGroup/{resourceGroupId}", method = RequestMethod.PUT)
		public ResponseEntity<ResourceGroup> updateResourceGroup(@PathVariable("resourceGroupId") long resourceGroupId, @RequestBody ResourceGroup resourceGroup) {
			ResourceGroup currentResourceGroup = resourceGroupService.findOne(resourceGroupId);
			if (currentResourceGroup == null) {
				return new ResponseEntity<ResourceGroup>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ResourceGroup>(resourceGroupService.save(resourceGroup), HttpStatus.OK);
		}

		// delete resourceGroup
		@RequestMapping(value = "/resourceGroup/{resourceGroupId}", method = RequestMethod.DELETE)
	    public ResponseEntity<ResourceGroup> deleteResourceGroup(@PathVariable("resourceGroupId") long resourceGroupId) {
	        ResourceGroup resourceGroup = resourceGroupService.findOne(resourceGroupId);
	        if (resourceGroup == null) {
	            return new ResponseEntity<ResourceGroup>(HttpStatus.NOT_FOUND);
	        }

	        resourceGroupService.delete(resourceGroupId);
	        return new ResponseEntity<ResourceGroup>(HttpStatus.NO_CONTENT);
	    }

		// show resourceGroup by id
		@RequestMapping(value = "/resourceGroup/{resourceGroupId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ResourceGroup> getResourceGroup(@PathVariable("resourceGroupId") long resourceGroupId) {
			ResourceGroup resourceGroup = resourceGroupService.findOne(resourceGroupId);
			if (resourceGroup == null) {
				return new ResponseEntity<ResourceGroup>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ResourceGroup>(resourceGroup, HttpStatus.OK);
		}

		// show all resourceGroups
		@RequestMapping(value = "/resourceGroup/", method = RequestMethod.GET)
		public ResponseEntity<List<ResourceGroup>> listAllResourceGroups() {
			List<ResourceGroup> resourceGroup = resourceGroupService.findAll();
			if (resourceGroup.isEmpty()) {
				return new ResponseEntity<List<ResourceGroup>>(HttpStatus.NO_CONTENT);											
			}
			return new ResponseEntity<List<ResourceGroup>>(resourceGroup, HttpStatus.OK);
		}
}
