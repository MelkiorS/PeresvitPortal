package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.peresvit.config.Constant;
import ua.peresvit.entity.Post;
import ua.peresvit.service.PostService;
import ua.peresvit.service.UserService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(value = "/admin/post")
public class PostController {

//    @Value("${file-upload-path}")
//    private String uploadPath;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPosts(Model model, @PageableDefault(value=3, direction = Sort.Direction.DESC, sort = "createDate") Pageable pageable){
        model.addAttribute("page", postService.findAll(pageable) );
        return "admin/post/allPosts";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model){
        model.addAttribute("post", postService.findOne(id));
        return "admin/post/showPost";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showCreate(Model model){
        model.addAttribute("post", new Post());
        return "admin/post/addPost";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createPost(@Valid Post postCreated, Errors validation, Model model, @RequestParam(name = "file") MultipartFile file){

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", postCreated);
            return "admin/post/addPost";
        }

        if(file != null && file.getSize() != 0){
            postCreated.setUrl(postService.saveFile(postCreated, file));
        } else if (postCreated.getPostId() != 0) {
            postCreated.setUrl(postService.findOne(postCreated.getPostId()).getUrl());
        }
        postCreated.setUser(userService.getCurrentUser());
        postCreated.setCreateDate(new Timestamp(System.currentTimeMillis()));

        postService.save(postCreated);

        return "redirect:/admin/post/";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String showEdit(@PathVariable Long id, Model model){
        Post post = postService.findOne(id);
        model.addAttribute("post", post);
        return "admin/post/addPost";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.POST)
    public String edit(@PathVariable Long id, @Valid Post postEdited, Errors val, Model model, @RequestParam(name = "file") MultipartFile file){

        if(val.hasErrors()){
            model.addAttribute("errors", val);
            model.addAttribute("post", postEdited);
            return "admin/post/addPost";
        }

        Post newPost = postService.findOne(id);
        newPost.setTitle(postEdited.getTitle());
        newPost.setBody(postEdited.getBody());
        if(file != null && file.getSize() != 0)
            newPost.setUrl(postService.saveFile(newPost, file));
        newPost.setCreateDate(new Timestamp(System.currentTimeMillis()));
        System.out.println(new Timestamp(System.currentTimeMillis()));
        postService.save(newPost);
        return "redirect:/admin/post/";
    }

    @RequestMapping(value = "{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deleteOne(id);
        return "redirect:/admin/post/";
    }
}
