package org.pepsik.web;

import org.joda.time.DateTime;
import org.pepsik.model.*;
import org.pepsik.model.Thread;
import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by pepsik on 4/9/15.
 */

@Controller
@RequestMapping("/thread")
public class ThreadController {

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
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = service.getAccount(loggedUser);
        thread.setAccount(account);
        thread.setWhen(new DateTime());
        service.saveThread(thread);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getThread(@PathVariable("id") long id, Model model) {
        model.addAttribute("thread", service.getThread(id));
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
        return "thread/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteThread(@PathVariable("id") long id) {
        service.deleteThread(id);
        return "redirect:/home";
    }

}
