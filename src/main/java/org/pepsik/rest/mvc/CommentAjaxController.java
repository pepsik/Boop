package org.pepsik.rest.mvc;

import org.joda.time.DateTime;
import org.pepsik.core.models.entities.Comment;
import org.pepsik.core.services.SmartService;
import org.pepsik.rest.exceptions.BadRequestException;
import org.pepsik.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 *  Controller is responsible to handle ajax request sent by the client browser.
 *  It have several methods that performs add, get(all), delete, edit operations with comments.
 *
 */

@Controller
@RequestMapping("/post/{postId}")
public class CommentAjaxController {

    private static final Logger logger = LoggerFactory.getLogger(CommentAjaxController.class);

    @Autowired
    private SmartService service;

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleIncorrectTextException(BadRequestException ex, HttpSession session) {
        ModelAndView model = new ModelAndView();
        BindingResult result = (BindingResult) session.getAttribute("errors");
        model.addObject("errors", result);
        model.setViewName("comment/ajax/error");
        return model;
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST, consumes = "application/json", produces = "text/html")
    @ResponseStatus(HttpStatus.CREATED)
    public String postComment(@PathVariable("postId") long postId, @RequestBody @Valid Comment comment, BindingResult result, Model model, HttpSession session) throws IOException {
        if (result.hasErrors()) {
            session.setAttribute("errors", result);
            throw new BadRequestException();
        }

        comment.setPost(service.getPost(postId));
        comment.setWhen(new DateTime());
        service.saveComment(comment);
        model.addAttribute(comment);
        return "comment/ajax/comment";
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String getComments(@PathVariable("postId") long postId, Model model) {
        model.addAttribute(service.getPost(postId).getComments());
        model.addAttribute("post_id", postId);
        return "comment/ajax/list_comments";
    }

    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void editComment(@PathVariable("commentId") long commentId, @RequestBody @Valid Comment editedComment, BindingResult result, HttpSession session) {
        if (!service.isExistComment(commentId))
            throw new ResourceNotFoundException();

        if (result.hasErrors()) {
            session.setAttribute("errors", result);
            throw new BadRequestException();
        }

        Comment comment = service.getComment(commentId);
        comment.setText(editedComment.getText());
        service.saveComment(comment);
    }

    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable("commentId") long commentId) {
        if (!service.isExistComment(commentId))
            throw new ResourceNotFoundException();

        service.deleteComment(commentId);
    }
}
