package org.pepsik.rest.mvc;

import org.joda.time.DateTime;
import org.pepsik.core.models.entities.Comment;
import org.pepsik.core.services.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 *
 */

//@Controller
//@RequestMapping(value = "/post/{post_id}/comment")
public class CommentController {

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newComment(@PathVariable("post_id") long post_id, Model model) {
        model.addAttribute(new Comment());
        model.addAttribute("post_id", post_id);
        return "comment/create";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editComment(@PathVariable("id") long id, HttpSession session, Model model) {
        Comment comment = service.getComment(id);
        session.setAttribute("comment", comment);
        model.addAttribute("comment", comment);
        model.addAttribute("post_id", comment.getPost().getId());
        return "comment/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String createComment(@PathVariable("post_id") long post_id, @Valid Comment post, BindingResult result) {
        if (result.hasErrors())
            return "comment/create";

        post.setWhen(new DateTime());
        post.setPost(service.getPost(post_id));
        service.saveComment(post);
        return "redirect:/post/" + post_id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String readComment(@PathVariable("id") long id, Model model) {
        model.addAttribute("comment", service.getComment(id));
        return "comment/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateComment(@PathVariable("id") long id, HttpSession session, @Valid Comment editedComment, BindingResult result) {
        if (result.hasErrors())
            return "comment/edit";

        Comment comment = (Comment) session.getAttribute("comment");
        comment.setText(editedComment.getText());
        service.saveComment(comment);
        session.removeAttribute("comment");
        return "redirect:/post/" + comment.getPost().getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteComment(@PathVariable("id") long id, @PathVariable("post_id") long post_id) {
        service.deleteComment(id);
        return "redirect:/post/" + post_id;
    }
}
