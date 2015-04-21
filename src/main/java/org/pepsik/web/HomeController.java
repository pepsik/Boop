package org.pepsik.web;

import org.pepsik.model.Account;
import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pepsik on 4/8/15.
 */

@Controller
public class HomeController {

    @Autowired
    private SmartService service;

    @RequestMapping(value = {"/", "/home"})
    public String showHomePage(Model model) {
        model.addAttribute("threadList", service.getAllThreads());
        return "home";
    }

    @RequestMapping(value = {"/login_fail"})
    public String loginFailPage() {
        return "login_fail";
    }
}
