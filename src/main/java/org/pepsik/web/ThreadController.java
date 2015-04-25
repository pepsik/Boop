package org.pepsik.web;

import org.joda.time.DateTime;
import org.pepsik.model.Post;
import org.pepsik.model.Thread;
import org.pepsik.service.SmartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by pepsik on 4/9/15.
 */

@Controller
@RequestMapping("/thread")
public class ThreadController {

    private static final Logger logger = LoggerFactory.getLogger(ThreadController.class);

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newThread(Model model) {
        model.addAttribute("thread", new Thread());
        return "thread/create";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editThread(@PathVariable("id") long id, HttpSession session, Model model) {
        Thread thread = service.getThread(id);
        session.setAttribute("thread", thread);
        model.addAttribute("thread", thread);
        return "thread/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String createThread(Thread thread) {    //TODO: Validation
        thread.setWhen(new DateTime());
        service.saveThread(thread);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getThread(@PathVariable("id") long id, Model model) {
        model.addAttribute("thread", service.getThread(id));
        logger.info("---GET---");
        return "thread/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateThread(@PathVariable("id") long id, Thread editedThread, HttpSession session, Model model) {
        //TODO: valid
        Thread thread = (Thread) session.getAttribute("thread");
        thread.setTitle(editedThread.getTitle());
        thread.setText(editedThread.getText());
        model.addAttribute("thread", thread);
        service.saveThread(thread);
        return "redirect:/thread/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteThread(@PathVariable("id") long id) {
        service.deleteThread(id);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{id}.html", method = RequestMethod.GET, produces = "text/html")
    public String getAjaxThreadComments(@PathVariable long id, Model model) {
        model.addAttribute(service.getThread(id).getPosts());
        model.addAttribute("thread_url", "thread/" + id);
        logger.info("---AJAX---");
        return "comments";
    }
}
