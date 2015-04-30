package org.pepsik.web;

import org.joda.time.DateTime;
import org.pepsik.model.Comment;
import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class CommentController {

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPost(@PathVariable("thread_id") long thread_id, Model model) {
        model.addAttribute("post", new Comment());
        model.addAttribute("thread_id", thread_id);
        model.addAttribute("thread_url", "thread/" + thread_id); //temp
        return "post/create";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") long id, HttpSession session, Model model) {
        Comment post = service.getComment(id);
        session.setAttribute("post", post);
        model.addAttribute("post", post);
        model.addAttribute("thread_id", post.getPost().getId());
        return "post/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String createPost(@PathVariable("thread_id") long thread_id, Comment post) {
        post.setWhen(new DateTime());
        post.setPost(service.getPost(thread_id));
        service.saveComment(post);
        return "redirect:/thread/" + thread_id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String readPost(@PathVariable("id") long id, Model model) {
        model.addAttribute("post", service.getComment(id));
        return "post/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updatePost(@PathVariable("id") long id, HttpSession session, Model model, Comment editedPost) {
        //TODO: valid
        Comment post = (Comment) session.getAttribute("post");
        post.setText(editedPost.getText());
        model.addAttribute("post", post);
        service.saveComment(post);
        return "redirect:/thread/" + post.getPost().getId();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public String deleteComment(@PathVariable("id") long id, @PathVariable("post_id") long post_id) {
//        service.deleteComment(id);
//        return "redirect:/thread/" + post_id;
//    }
}
