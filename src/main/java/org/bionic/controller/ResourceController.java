package org.bionic.controller;

import java.util.Collection;

import javax.servlet.annotation.MultipartConfig;

import org.bionic.entity.EnumResourceType;
import org.bionic.entity.Resource;
import org.bionic.entity.ResourceType;
import org.bionic.service.ResourceGroupService;
import org.bionic.service.ResourceService;
import org.bionic.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin/resource")
@MultipartConfig(fileSizeThreshold = 1024*1024*20)
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ResourceGroupService resourceGroupService;
	@Autowired
	private ResourceTypeService resourceTypeService;
		
	//go to manage page
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String goToManagement(Model model) {
		return "admin/resource/resourceManagement";
	}

	//go to TEXT PHOTO addForm
	@RequestMapping(value = "/add/group/{resourceGroupId}", method = RequestMethod.GET)
	public String goToAddForm(@PathVariable("resourceGroupId") long resourceGroupId, Model model) {
		Resource resource = new Resource();
		resource.setResourceGroup(resourceGroupService.findOne(resourceGroupId));
		model.addAttribute("resource", resource);
		return "admin/resource/addResource";
	}

	//go to  VIDEO addForm
	@RequestMapping(value = "/addVideo/group/{resourceGroupId}", method = RequestMethod.GET)
	public String goToAddVideoForm(@PathVariable("resourceGroupId") long resourceGroupId, Model model) {
		Resource resource = new Resource();
		resource.setResourceGroup(resourceGroupService.findOne(resourceGroupId));
		model.addAttribute("resource", resource);
		return "admin/resource/addVideoResource";
	}
	
	 // create resource
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String createResource(Resource resource, RedirectAttributes model, @RequestParam("file") MultipartFile file) {
		resource.setUrl(resourceService.saveFile(resource, file));
		resourceService.save(resource);
		model.addAttribute("resourceId", resource.getResourceId());
		model.addFlashAttribute("resource", resource);
		return "redirect:/admin/resource/{resourceId}";
	}

	// create video resource
	@RequestMapping(value = "/video", method = RequestMethod.POST)
	public String createVideoResource(Resource resource, RedirectAttributes model) {
		ResourceType resourceType = resourceTypeService.findOne(EnumResourceType.VIDEO.getValue());
		resource.setResourceType(resourceType);
		resourceService.save(resource);
		model.addAttribute("resourceId", resource.getResourceId());
		model.addFlashAttribute("resource", resource);
		return "redirect:/admin/resource/{resourceId}";
	}
	
	// show resource by id
	@RequestMapping(value = "/{resourceId}", method = RequestMethod.GET)
	public String getResource(@PathVariable("resourceId") long resourceId, Model model) {
		if (!model.containsAttribute("resource")) 
			model.addAttribute("resource", resourceService.findOne(resourceId));
		return "admin/resource/resourceProfile";
	}

	// show all resources
	@RequestMapping(value = "/group/{resourceGroupId}", method = RequestMethod.GET)
	public String listAllResources(@PathVariable("resourceGroupId") long resourceGroupId, Model model) {
		Collection<Resource> resources = resourceGroupService.findOne(resourceGroupId).getResourceCollection();
		model.addAttribute("resourceList", resources);
		model.addAttribute("resource", new Resource());
		return "admin/resource/allResources";
	}
	
	// delete resource
	// need to solve issue when its FK to smth !!!!
	@RequestMapping(value = "/delete/{resourceId}", method = RequestMethod.GET)
    public String deleteResource(@PathVariable("resourceId") long resourceId,
    		Model model) {
        Resource resource = resourceService.findOne(resourceId);
        long resourceGroupId = resource.getResourceGroup().getResourceGroupId();
        if (resource == null) {
           // custom exception
        }
        resourceService.delete(resource);
        return listAllResources(resourceGroupId,model);
    }
	
	// edit resource
	@RequestMapping(value = "/edit/{resourceId}", method = RequestMethod.GET)
    public String editResource(@PathVariable("resourceId")  long resourceId,
    		Model model) {
        Resource resource = resourceService.findOne(resourceId);
        if (resource == null) {
           // custom exception
        }
        model.addAttribute("resource", resource);
        return "admin/resource/addResource";
    }	
}
