package org.pepsik.web;

import org.joda.time.DateTime;
import org.pepsik.model.Post;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by pepsik on 4/27/15.
 */
@Controller
@RequestMapping("/thread/{threadId}")
public class AjaxController {

    private static final Logger logger = LoggerFactory.getLogger(ThreadController.class);

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = "application/json", produces = "text/html")
    @ResponseStatus(HttpStatus.CREATED)
    public String postThreadComment(@PathVariable("threadId") long threadId, @RequestBody @Valid Post post, BindingResult result, Model model) {
        logger.info("---POST AJAX---");
        if (!service.isExistThread(threadId))
            throw new ResourceNotFoundException();

        if (result.hasErrors()) {
//            model.addAttribute(post);                //temp test
            logger.info("---Not Valid Post---");
            logger.info(result.getFieldError().toString());
            throw new BadRequestException();
        } else {
            post.setThread(service.getThread(threadId));
            post.setWhen(new DateTime());
            model.addAttribute(post);
            service.savePost(post);
        }

//        model.addAttribute(service.getThread(threadId).getPosts());
//        model.addAttribute("thread_url", "thread/" + threadId);     //del
//        model.addAttribute("thread_id", threadId);
        return "post/ajax/new_comment";
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET, produces = "text/html")
    public String getThreadComments(@PathVariable("threadId") long threadId, Model model) {
        logger.info("---GET AJAX---");
        model.addAttribute(service.getThread(threadId).getPosts());
        model.addAttribute("thread_url", "thread/" + threadId);   //del
        model.addAttribute("thread_id", threadId);
        model.addAttribute(new Post());
        return "post/ajax/comments";
    }

    @RequestMapping(value = "/post/{commentId}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void editThreadComment(@PathVariable("commentId") long commentId, @RequestBody @Valid Post post, BindingResult result) {
        logger.info("---EDIT AJAX---");
        if (!service.isExistPost(commentId))
            throw new ResourceNotFoundException();

        if (result.hasErrors())
            return;              //how proceed valid errors ?

        Post editablePost = service.getPost(commentId);
        editablePost.setText(post.getText());
        service.savePost(editablePost);
    }

    @RequestMapping(value = "/post/{commentId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteThreadComment(@PathVariable("commentId") long commentId) {
        logger.info("---DELETE AJAX---");
        if (service.isExistPost(commentId))
            throw new ResourceNotFoundException();

        service.deletePost(commentId);
    }
}
