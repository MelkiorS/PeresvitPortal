package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.peresvit.entity.*;
import ua.peresvit.service.*;


@Controller
@RequestMapping(value = "/resource")
public class UserArticlesController {
    private final
    ResourceGroupTypeChapterService chapterService;
    private final
    UserService userService;
    private final
    ResourceGroupTypeService resourceGroupTypeService;
    private final ArticleService articleService;

    @Autowired
    public UserArticlesController(ResourceGroupTypeChapterService chapterService, UserService userService, ResourceGroupTypeService resourceGroupTypeService, ArticleService articleService) {
        this.chapterService = chapterService;
        this.userService = userService;
        this.resourceGroupTypeService = resourceGroupTypeService;
        this.articleService = articleService;
    }

    // go to article
    @RequestMapping(value = "article/{articleId}", method = RequestMethod.GET)
    public String showArticle(@PathVariable long articleId, Model model) {
        Article article = articleService.findOne(articleId);
        model.addAttribute("article", article);
        return "home/studyingMaterial";
    }

    // go to myWay
    @RequestMapping(value = "/myWay/{id}", method = RequestMethod.GET)
    public String goToMyWay(@PathVariable long id, Model model) {
        ResourceGroupType resourceGroupTypes = resourceGroupTypeService.findOne(id);
        resourceGroupTypes.setChapterList(chapterService.findAllByResourceGroupType(resourceGroupTypes));
        User currentUser = userService.getCurrentUser();
        switch (currentUser.getRole().getRoleName()) {
            case "ADMIN" : {
                resourceGroupTypes.getChapterList().forEach(c->c.setArticleCollection(articleService.findAllByChapterIdAndResourceGroupType
                        (c.getChapterId(), resourceGroupTypes)));
                break;
            }
            default : {
                resourceGroupTypes.getChapterList().forEach(c->c.setArticleCollection(articleService.findAllByChapterIdAndResourceGroupTypeAndRang
                        (c.getChapterId(), resourceGroupTypes, currentUser.getRole())));
                break;
            }
        }
        model.addAttribute("resourceGroupTypeList", resourceGroupTypes); // adding types for select
        return "home/myWay";
    }
}
