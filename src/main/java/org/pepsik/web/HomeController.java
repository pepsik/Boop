package org.pepsik.web;

import org.pepsik.model.Account;
import org.pepsik.service.SmartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private SmartService service;

    @RequestMapping(value = {"/", "/home"})
    public String showHomePage(Model model) {
        model.addAttribute(model.addAttribute(service.getThreadsByPage(1)));
        model.addAttribute("pagination", service.getPagination(1));
        model.addAttribute("currentPageIndex", 1);
        logger.info("----Home----");
        return "home";
    }

    @RequestMapping(value = {"/login_fail"})
    public String loginFailPage() {
        return "login_fail";
    }
}
