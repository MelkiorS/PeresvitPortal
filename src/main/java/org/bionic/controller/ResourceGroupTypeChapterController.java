package org.bionic.controller;

import org.bionic.entity.Rang;
import org.bionic.entity.ResourceGroupType;
import org.bionic.entity.ResourceGroupTypeChapter;
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
@RequestMapping(value = "/admin/chapter")
public class ResourceGroupTypeChapterController {
    @Autowired
    private ResourceGroupTypeChapterService resourceGroupTypeChapterService;
    @Autowired
    private ResourceGroupTypeService resourceGroupTypeService;
    @Autowired
    private RangService rangService;

    //go to manage pagel
    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String goToManagement(Model model) {
        return "admin/chapter/chapterManagement";
    }
    //go to addForm
    @RequestMapping(value = "/add/group/{groupId}", method = RequestMethod.GET)
    public String goToAddForm(@PathVariable("groupId") long groupId, Model model) {
        List<ResourceGroupType> resourceGroupTypes = resourceGroupTypeService.findAll();
        ResourceGroupTypeChapter chapter = new ResourceGroupTypeChapter();
        chapter.setResourceGroupType(resourceGroupTypeService.findOne(groupId));
        model.addAttribute("chapter", chapter);   // addig empty object for post form
        model.addAttribute("resourceGroupTypeList", resourceGroupTypes); // adding types for select
        return "admin/chapter/addChapter";
    }

    // create chapter )
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createChapter(ResourceGroupTypeChapter chapter, RedirectAttributes model) {
        resourceGroupTypeChapterService.save(chapter);
        model.addAttribute("chapterId", chapter.getChapterId());
        model.addFlashAttribute("chapter", chapter); // adding attribute that will be alive in two requests
        return "redirect:/admin/article/add";
    }

    // show chapter by id
    @RequestMapping(value = "/{chapterId}", method = RequestMethod.GET)
    public String getChapter(@PathVariable("chapterId") long chapterId, Model model) {
        if (!model.containsAttribute("chapter"))  // if it's not redirect add
            model.addAttribute("chapter", resourceGroupTypeChapterService.findOne(chapterId));
        return "admin/chapter/chapterProfile";
    }

    // show all chapters
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listAllChapters(Model model) {
        List<ResourceGroupTypeChapter> chapters = resourceGroupTypeChapterService.findAll();
        model.addAttribute("chapterList", chapters);
        return "admin/chapter/allChapters";
    }

    // delete chapter
    // need to solve issue when its FK to smth !!!!
    @RequestMapping(value = "/delete/{chapterId}", method = RequestMethod.GET)
    public String deleteChapter(@PathVariable("chapterId") long chapterId,
                                Model model) {
        ResourceGroupTypeChapter chapter = resourceGroupTypeChapterService.findOne(chapterId);
        if (chapter == null) {
            // custom exception
        }
        resourceGroupTypeChapterService.delete(chapter);
        return listAllChapters(model); // after deleting show all
    }



    // edit chapter
    @RequestMapping(value = "/edit/{chapterId}", method = RequestMethod.GET)
    public String editChapter(@PathVariable("chapterId")  long chapterId,
                              Model model) {
        ResourceGroupTypeChapter chapter = resourceGroupTypeChapterService.findOne(chapterId); //taking current chapter
        List<ResourceGroupType> resourceGroupTypes = resourceGroupTypeService.findAll();
        List<Rang> rangTypes = rangService.findAll();
        if (chapter == null) {
            // custom exception
        }
        model.addAttribute("chapter", chapter); // object is not empty
        model.addAttribute("rangList", rangTypes);
        model.addAttribute("resourceGroupTypeList", resourceGroupTypes);
        return "admin/chapter/addChapter"; // sending to addForm
    }
}
