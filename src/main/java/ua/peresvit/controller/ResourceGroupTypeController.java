package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.peresvit.entity.ResourceGroupType;
import ua.peresvit.service.ResourceGroupTypeService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/resourceGroupType")
public class ResourceGroupTypeController {
	@Autowired
	private ResourceGroupTypeService resourceGroupTypeService;
		
	//go to manage page
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String goToManagement(Model model) {
		return "admin/resourceGroupType/resourceGroupTypeManagement";
	}
	//go to addForm
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goToAddForm(Model model) {
		model.addAttribute(new ResourceGroupType());
		return "admin/resourceGroupType/addResourceGroupType";
	}
	
	 // create resourceGroupType
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String createResourceGroupType(ResourceGroupType resourceGroupType, RedirectAttributes model) {
		resourceGroupTypeService.save(resourceGroupType);
		model.addAttribute("resourceGroupTypeId", resourceGroupType.getResourceGroupTypeId());
		model.addFlashAttribute("resourceGroupType", resourceGroupType);
		return "redirect:/admin/resourceGroupType/{resourceGroupTypeId}";
	}
	
	// show resourceGroupType by id
	@RequestMapping(value = "/{resourceGroupTypeId}", method = RequestMethod.GET)
	public String getResourceGroupType(@PathVariable("resourceGroupTypeId") long resourceGroupTypeId, Model model) {
		if (!model.containsAttribute("resourceGroupType")) 
			model.addAttribute("resourceGroupType", resourceGroupTypeService.findOne(resourceGroupTypeId));
		return "admin/resourceGroupType/resourceGroupTypeProfile";
	}

	// show all resourceGroupTypes
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listAllResourceGroupTypes(Model model) {
		List<ResourceGroupType> resourceGroupTypes = resourceGroupTypeService.findAll();
		model.addAttribute("resourceGroupTypeList", resourceGroupTypes);
		model.addAttribute("resourceGroupType", new ResourceGroupType());
		return "admin/resourceGroupType/allResourceGroupTypes";
	}
	
	// delete resourceGroupType
	// need to solve issue when its FK to smth !!!!
	@RequestMapping(value = "/delete/{resourceGroupTypeId}", method = RequestMethod.GET)
    public String deleteResourceGroupType(@PathVariable("resourceGroupTypeId") long resourceGroupTypeId,
    		Model model) {
        ResourceGroupType resourceGroupType = resourceGroupTypeService.findOne(resourceGroupTypeId);
        if (resourceGroupType == null) {
           // custom exception
        }
        resourceGroupTypeService.delete(resourceGroupType);
        return listAllResourceGroupTypes(model);
    }
	
	// edit resourceGroupType
	@RequestMapping(value = "/edit/{resourceGroupTypeId}", method = RequestMethod.GET)
    public String editResourceGroupType(@PathVariable("resourceGroupTypeId")  long resourceGroupTypeId,
    		Model model) {
        ResourceGroupType resourceGroupType = resourceGroupTypeService.findOne(resourceGroupTypeId);
        if (resourceGroupType == null) {
           // custom exception
        }
        model.addAttribute("resourceGroupType", resourceGroupType);
        return "admin/resourceGroupType/addResourceGroupType";
    }
	
}
