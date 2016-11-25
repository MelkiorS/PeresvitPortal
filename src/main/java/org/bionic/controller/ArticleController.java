package org.bionic.controller;

import org.bionic.entity.Article;
import org.bionic.entity.Rang;
import org.bionic.entity.ResourceGroupType;
import org.bionic.entity.ResourceGroupTypeChapter;
import org.bionic.service.ArticleService;
import org.bionic.service.RangService;
import org.bionic.service.ResourceGroupTypeChapterService;
import org.bionic.service.ResourceGroupTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping(value = "/admin/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ResourceGroupTypeService resourceGroupTypeService;
    @Autowired
    private ResourceGroupTypeChapterService chapterService;
    @Autowired
    private RangService rangService;

    //go to manage page
    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String goToManagement(Model model) {
        return "admin/article/articleManagement";
    }
    //go to addForm
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToAddForm(Model model) {
        List<ResourceGroupType> resourceGroupTypes = resourceGroupTypeService.findAll();
        List<Rang> rangTypes = rangService.findAll();
        resourceGroupTypes.stream().forEach(p->p.
               setChapterList(chapterService.findAllByResourceGroupType(p)));
        model.addAttribute(new Article());   // addig empty object for post form
        model.addAttribute("rangList", rangTypes); // adding list of rang for select
        model.addAttribute("resourceGroupTypeList", resourceGroupTypes); // adding types for select
        return "admin/article/addArticle";
    }

    // create article )
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createArticle(Article article, RedirectAttributes model) {
        long id  = article.getResourceGroupType().getResourceGroupTypeId();
        article.setResourceGroupType(resourceGroupTypeService.findOne(id));
        articleService.save(article);
        model.addAttribute("articleId", article.getArticleId());
        model.addFlashAttribute("article", article); // adding attribute that will be alive in two requests
        return "redirect:/admin/article/{articleId}"; // just not to redownload it again
    }

    // show article by id
    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public String getArticle(@PathVariable("articleId") long articleId, Model model) {
        if (!model.containsAttribute("article"))  // if it's not redirect add
            model.addAttribute("article", articleService.findOne(articleId));
        return "admin/article/articleProfile";
    }

    // show all articles
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listAllArticles(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articleList", articles);
        return "admin/article/allArticles";
    }

    // delete article
    // need to solve issue when its FK to smth !!!!
    @RequestMapping(value = "/delete/{articleId}", method = RequestMethod.GET)
    public String deleteArticle(@PathVariable("articleId") long articleId,
                                      Model model) {
        Article article = articleService.findOne(articleId);
        if (article == null) {
            // custom exception
        }
        articleService.delete(article);
        return listAllArticles(model); // after deleting show all
    }



    // edit article
    @RequestMapping(value = "/edit/{articleId}", method = RequestMethod.GET)
    public String editArticle(@PathVariable("articleId")  long articleId,
                                    Model model) {
        Article article = articleService.findOne(articleId); //taking current article
        List<ResourceGroupType> resourceGroupTypes = resourceGroupTypeService.findAll();
        List<Rang> rangTypes = rangService.findAll();
        if (article == null) {
            // custom exception
        }
        model.addAttribute("article", article); // object is not empty
        model.addAttribute("rangList", rangTypes);
        model.addAttribute("resourceGroupTypeList", resourceGroupTypes);
        return "admin/article/addArticle"; // sending to addForm
    }
}
