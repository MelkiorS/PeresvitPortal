package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.peresvit.entity.*;
import ua.peresvit.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/resource")
public class UserResourceController {
    @Autowired
    ResourceGroupTypeChapterService chapterService;
    @Autowired
    ResourceGroupService resourceGroupService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    ResourceGroupTypeService resourceGroupTypeService;
    @Autowired
    private ArticleService articleService;

    // go to article
    @RequestMapping(value = "article/{articlerId}", method = RequestMethod.GET)
    public String showArticle(@PathVariable long articlerId, Model model) {
        Article article = articleService.findOne(articlerId);
        model.addAttribute("article", article);
        return "resource/studyingMaterial";
    }
//    @RequestMapping(value = "/type/{groupName}", method = RequestMethod.GET)
//    public String getArticles(@PathVariable String groupName, Model model, HttpServletRequest request) {
//        // need to take userId from session
//        HttpSession session = request.getSession();
//        User user = (User)session.getAttribute("user");
////        Role rang = user.getRole();
//        //Role rang = roleService.findOne(1l);
//        ResourceGroupType type = resourceGroupTypeService.findResourceGroupTypeByGroupName(groupName);
//        Collection<Article> articles = articleService.findAllByResourceGroupTypeAndRang(type,rang);
//        articles.forEach(System.out::println);
//        if (articles.size() > 1){
//            model.addAttribute("articleList", articles);
//        }
//        else {
//            model.addAttribute("article", articles);
//        }
//        return "resource/studyingMaterial";
//    }

    // go to myWay
    @RequestMapping(value = "/myWay/{id}", method = RequestMethod.GET)
    public String goToMyWay(@PathVariable long id, Model model) {
        ResourceGroupType resourceGroupTypes = resourceGroupTypeService.findOne(id);
        List<Role> roleList = roleService.findAll();
        resourceGroupTypes.setChapterList(chapterService.findAllByResourceGroupType(resourceGroupTypes));
        resourceGroupTypes.getChapterList().forEach(c->c.setArticleCollection(articleService.findAllByChapterIdAndResourceGroupTypeAndRang(
                c.getChapterId(), resourceGroupTypes, userService.getCurrentUser().getRole()
        )));
        model.addAttribute(new Article());   // addig empty object for post form
        model.addAttribute("rangList", roleList); // adding list of rang for select
        model.addAttribute("resourceGroupTypeList", resourceGroupTypes); // adding types for select
        return "user/myWay";
    }

//    // go to myWay
    @RequestMapping(value = "/myWay", method = RequestMethod.GET)
    public String goToMyWay(Model model) {
        List<ResourceGroupType> resourceGroupTypes = resourceGroupTypeService.findAll();
        List<Role> roleList = roleService.findAll();
        resourceGroupTypes.forEach(p->p.setChapterList(chapterService.findAllByResourceGroupType(p)));
        resourceGroupTypes.forEach(r->r.getChapterList().forEach(c->c.setArticleCollection(articleService.findAllByChapterIdAndResourceGroupTypeAndRang(
                c.getChapterId(), r, userService.getCurrentUser().getRole()
        ))));
        model.addAttribute(new Article());   // addig empty object for post form
        model.addAttribute("rangList", roleList); // adding list of rang for select
        model.addAttribute("resourceGroupTypeList", resourceGroupTypes); // adding types for select
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("groups", resourceGroupTypeService.findAll());
        return "user/myWay";
    }
    // show resources for current user depending on his name
    @RequestMapping(value = "/{groupName}", method = RequestMethod.GET)
    public String getResourceGroup(@PathVariable String groupName, Model model, HttpServletRequest request) {
        // need to take userId from session
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            Role role = user.getRole();
            ResourceGroupType type = resourceGroupTypeService.findResourceGroupTypeByGroupName(groupName);
            ResourceGroup resourceGroup = resourceGroupService.findResourceGroupByResourceGroupTypeAndRang(type, role);
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
