package org.pepsik.web;

import org.joda.time.DateTime;
import org.pepsik.model.Account;
import org.pepsik.model.Post;
import org.pepsik.service.SmartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;

/**
 * Created by pepsik on 4/9/15.
 */

@Controller
@RequestMapping(value = "/thread/{thread_id}/post")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPost(@PathVariable("thread_id") long thread_id, Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("thread_id", thread_id);
        return "post/create";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") long id, HttpSession session, Model model) {
        Post post = service.getPost(id);
        session.setAttribute("post", post);
        model.addAttribute("post", post);
        model.addAttribute("thread_id", post.getThread().getId());
        return "post/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String createPost(@PathVariable("thread_id") long thread_id, Post post) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(loggedUser);
        Account account = service.getAccount(loggedUser);
        post.setAccount(account);
        post.setWhen(new DateTime());
        post.setThread(service.getThread(thread_id));
        service.savePost(post);
        return "redirect:/thread/" + thread_id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String readPost(@PathVariable("id") long id, Model model) {
        model.addAttribute("post", service.getPost(id));
        return "post/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updatePost(@PathVariable("id") long id, HttpSession session, Model model, Post editedPost) {
        //TODO: valid
        Post post = (Post) session.getAttribute("post");
        post.setText(editedPost.getText());
        model.addAttribute("post", post);
        service.savePost(post);
        return "redirect:/thread/" + post.getThread().getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePost(@PathVariable("id") long id, @PathVariable("thread_id") long thread_id) {
        service.deletePost(id);
        return "redirect:/thread/" + thread_id;
    }
}
