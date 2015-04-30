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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by pepsik on 4/27/15.
 */
@Controller
@RequestMapping("/post/{postId}")
public class AjaxController {

    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/comment", method = RequestMethod.POST, consumes = "application/json", produces = "text/html")
    @ResponseStatus(HttpStatus.CREATED)
    public String postComment(@PathVariable("postId") long postId, @RequestBody @Valid Comment comment, BindingResult result, Model model) {
        logger.info("---POST AJAX---");
        if (!service.isExistPost(postId))
            throw new ResourceNotFoundException();

        if (result.hasErrors()) {
            logger.info("---No Valid Comment---");
            throw new BadRequestException();          //something do
        } else {
            comment.setPost(service.getPost(postId));
            comment.setWhen(new DateTime());
            service.saveComment(comment);
        }
        model.addAttribute(comment);
        return "comment/ajax/new_comment";
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String getComments(@PathVariable("postId") long postId, Model model) {
        logger.info("---GET AJAX---");
        if (!service.isExistPost(postId))
            throw new ResourceNotFoundException();

        model.addAttribute(service.getPost(postId).getComments());
        model.addAttribute("post_id", postId);
        model.addAttribute(new Comment());
        return "comment/ajax/comments";
    }

    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void editComment(@PathVariable("commentId") long commentId, @RequestBody @Valid Comment comment, BindingResult result) {
        logger.info("---EDIT AJAX---");
        if (!service.isExistComment(commentId))
            throw new ResourceNotFoundException();

        if (result.hasErrors())
            return;              //how proceed valid errors ?

        Comment editableComment = service.getComment(commentId);
        editableComment.setText(comment.getText());
        service.saveComment(editableComment);
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
