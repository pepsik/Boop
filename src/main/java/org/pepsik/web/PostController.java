package org.pepsik.web;

import org.joda.time.DateTime;
import org.pepsik.model.Post;
import org.pepsik.service.SmartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by pepsik on 4/9/15.
 */

@Controller
@RequestMapping("/post")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") long id, HttpSession session, Model model) {
        Post post = service.getPost(id);
        session.setAttribute("post", post);
        model.addAttribute(post);
        return "post/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createPost(@Valid Post post, BindingResult result) {

        if (result.hasErrors())
            return "post/create";

        post.setWhen(new DateTime());
        service.savePost(post);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPost(@PathVariable("id") long id, Model model) {
        model.addAttribute(service.getPost(id));
        return "post/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updatePost(@PathVariable("id") long id, @Valid Post updatedPost, BindingResult result, HttpSession session) {

        if (result.hasErrors())
            return "post/edit";

        final Post post = (Post) session.getAttribute("post");
        updatedPost.setId(post.getId());
        updatedPost.setWhen(post.getWhen());
        updatedPost.setUser(post.getUser());
        updatedPost.setComments(post.getComments());
        service.savePost(updatedPost);
        session.removeAttribute("post");
        return "redirect:/post/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePost(@PathVariable("id") long id) {
        service.deletePost(id);
        return "redirect:/home";
    }
}
