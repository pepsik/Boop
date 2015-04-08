package org.pepsik.web;

import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute(service.getAllThreads());
        return "index";
    }
}
