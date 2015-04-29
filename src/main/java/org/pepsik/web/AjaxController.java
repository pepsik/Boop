package org.pepsik.web;

import org.joda.time.DateTime;
import org.pepsik.model.Post;
import org.pepsik.service.SmartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pepsik on 4/27/15.
 */
@Controller
@RequestMapping("/thread/{threadId}")
public class AjaxController {

    private static final Logger logger = LoggerFactory.getLogger(ThreadController.class);

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/post", method = RequestMethod.POST, produces = "text/html", consumes = "application/json")
    public String postThreadComment(@PathVariable("threadId") long threadId, @RequestBody Post post, Model model) {
        post.setWhen(new DateTime());
        post.setThread(service.getThread(threadId));
        logger.info("---POST AJAX---");
        service.savePost(post);
        model.addAttribute(service.getThread(threadId).getPosts());
        model.addAttribute("thread_url", "thread/" + threadId);     //del
        model.addAttribute("thread_id", threadId);
        model.addAttribute(new Post());
        return "comments";
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET, produces = "text/html")
    public String getThreadComments(@PathVariable("threadId") long threadId, Model model) {
        model.addAttribute(service.getThread(threadId).getPosts());
        model.addAttribute("thread_url", "thread/" + threadId);   //del
        model.addAttribute("thread_id", threadId);
        model.addAttribute(new Post());
        logger.info("---GET AJAX---");
        return "comments";
    }

    @RequestMapping(value = "/post/{commentId}", method = RequestMethod.PUT, consumes = "application/json", produces = "text/html")
    public void editThreadComment(@PathVariable("commentId") long commentId, @RequestBody Post post) {
        logger.info("---EDIT AJAX---");
        Post editablePost = service.getPost(commentId);
        editablePost.setText(post.getText());
        service.savePost(editablePost);
    }

    @RequestMapping(value = "/post/{commentId}", method = RequestMethod.DELETE, produces = "text/html")
    public void deleteThreadComment(@PathVariable("commentId") long commentId) {
        logger.info("---DELETE AJAX---");
        service.deletePost(commentId);
    }
}
