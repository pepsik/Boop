package org.pepsik.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.joda.time.DateTime;
import org.pepsik.model.*;
import org.pepsik.model.Thread;
import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pepsik on 4/9/15.
 */

//TODO: secur methods

@Controller
@RequestMapping("/thread")
public class ThreadController {

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newThread(Model model) {
        model.addAttribute("newThread", new Thread());
        return "thread/create";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editThread(@PathVariable("id") long id, Model model) {
        model.addAttribute("thread", service.getThread(id));
        return "thread/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String createThread(Thread thread) {
        Account account = new Account();
        account.setUserName("admin");
        thread.setAccount(account);
        thread.setWhen(new DateTime());

        //TODO: Validation
        service.saveThread(thread);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String readThread(@PathVariable("id") long id, Model model) {
        model.addAttribute("thread", service.getThread(id));
        return "thread/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateThread(@RequestParam("id") long id, Model model, Thread thread) {
        //TODO: valid
        model.addAttribute("thread", thread);
        return "thread/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteThread(@PathVariable("id") long id) {
        service.deleteThread(id);
        return "redirect:/home";
    }

}
