package org.bionic.controller;

import org.bionic.dao.RangRepository;
import org.bionic.dao.ResourceGroupRepository;
import org.bionic.dao.ResourceGroupTypeRepository;
import org.bionic.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 10.11.16.
 */
@Controller
@RequestMapping(value = "/resource")
public class UserResourceController {
    @Autowired
    ResourceGroupRepository resourceGroupRepository;
    @Autowired
    RangRepository rangRepository;
    @Autowired
    ResourceGroupTypeRepository resourceGroupTypeRepository;

    // go to myWay
    @RequestMapping(value = "/myWay", method = RequestMethod.GET)
    public String goToMyWay(Model model) {
        return "user/myWay";
    }
    // show resources for current user depending on his name
    @RequestMapping(value = "/{groupName}", method = RequestMethod.GET)
    public String getResourceGroup(@PathVariable String groupName, Model model, HttpServletRequest request) {
        // need to take userId from session
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            Rang rang = user.getRang();
            ResourceGroupType type = resourceGroupTypeRepository.findResourceGroupTypeByGroupName(groupName);
            ResourceGroup resourceGroup = resourceGroupRepository.findResourceGroupByResourceGroupTypeAndRang(type, rang);
            Collection<Resource> resourceCollection = resourceGroup.getResourceCollection();
            groupResourceByTypes(resourceCollection, model);
        }
        catch(Exception e){
            return "error/emptyResourceGroup";
        }
        //model.addAttribute("resourceList", resourceGroup.getResourceCollection());
        return "resource/studyingMaterial";
    }

    public void groupResourceByTypes(Collection<Resource> resourceCollection,Model model){
        //adding to model text group
        List<Resource> resourceListText = resourceCollection.stream()
                .filter(r-> "TEXT".equals(r.getResourceType().getTypeName()))
                .collect(Collectors.toList());
        model.addAttribute("resourceListText", resourceListText);
        //adding to model photo group
        List<Resource> resourceListPhoto = resourceCollection.stream()
                .filter(r-> "PHOTO".equals(r.getResourceType().getTypeName()))
                .collect(Collectors.toList());
        model.addAttribute("resourceListPhoto", resourceListPhoto);
        //adding to model video group
        List<Resource> resourceListVideo = resourceCollection.stream()
                .filter(r-> "VIDEO".equals(r.getResourceType().getTypeName()))
                .collect(Collectors.toList());
        model.addAttribute("resourceListVideo", resourceListVideo);
    }
}
