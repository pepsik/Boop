package org.pepsik.web;

import org.joda.time.DateTime;
import org.pepsik.model.Post;
import org.pepsik.service.SmartService;
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
        model.addAttribute("post", post);
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
        model.addAttribute("post", service.getPost(id));
        return "post/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updatePost(@PathVariable("id") long id, @Valid Post post, BindingResult result, HttpSession session, Model model) {

        if (result.hasErrors())
            return "post/edit";

        Post editablePost = (Post) session.getAttribute("post");
        editablePost.setTitle(post.getTitle());
        editablePost.setText(post.getText());
        model.addAttribute("post", editablePost);
        service.savePost(editablePost);
        return "redirect:/post/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePost(@PathVariable("id") long id) {
        service.deletePost(id);
        return "redirect:/home";
    }
}
