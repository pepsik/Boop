package org.pepsik.web;

import org.joda.time.DateTime;
import org.pepsik.model.Comment;
import org.pepsik.service.SmartService;
import org.pepsik.web.exception.BadRequestException;
import org.pepsik.web.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by pepsik on 4/27/15.
 */
@Controller
@RequestMapping("/post/{postId}")
public class AjaxController {

    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

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
        logger.info("---POST AJAX---");
        if (!service.isExistPost(postId))
            throw new ResourceNotFoundException();

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
        logger.info("---GET AJAX---");
        if (!service.isExistPost(postId))
            throw new ResourceNotFoundException();

        model.addAttribute(service.getPost(postId).getComments());
        model.addAttribute("post_id", postId);
        return "comment/ajax/comments";
    }

    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void editComment(@PathVariable("commentId") long commentId, @RequestBody @Valid Comment editedComment, BindingResult result, HttpSession session) {
        logger.info("---EDIT AJAX---");
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

    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable("commentId") long commentId) {
        logger.info("---DELETE AJAX---");
        if (!service.isExistComment(commentId))
            throw new ResourceNotFoundException();

        service.deleteComment(commentId);
    }
}
