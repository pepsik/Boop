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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by pepsik on 4/9/15.
 */

@Controller
@RequestMapping("/thread")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newThread(Model model) {
        model.addAttribute("thread", new Post());
        return "thread/create";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editThread(@PathVariable("id") long id, HttpSession session, Model model) {
        Post thread = service.getPost(id);
        session.setAttribute("thread", thread);
        model.addAttribute("thread", thread);
        return "thread/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String createThread(Post thread) {    //TODO: Validation
        thread.setWhen(new DateTime());
        service.savePost(thread);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getThread(@PathVariable("id") long id, Model model) {
        model.addAttribute("thread", service.getPost(id));
        return "thread/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateThread(@PathVariable("id") long id, Post editedThread, HttpSession session, Model model) {
        //TODO: valid
        Post thread = (Post) session.getAttribute("thread");
        thread.setTitle(editedThread.getTitle());
        thread.setText(editedThread.getText());
        model.addAttribute("thread", thread);
        service.savePost(thread);
        return "redirect:/thread/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteThread(@PathVariable("id") long id) {
        service.deletePost(id);
        return "redirect:/home";
    }
}
