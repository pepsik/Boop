package org.pepsik.web;

import org.pepsik.model.Post;
import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private SmartService service;

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public List<Post> showHomePage(Model model) {
//        model.addAttribute(model.addAttribute(service.getPostsByPage(1)));
//        model.addAttribute("pagination", service.getPagination(1, service.getAllPostsCount()));
//        model.addAttribute("currentPageIndex", 1);
        return service.getPostsByPage(1);
    }

    @RequestMapping(value = "/login_fail", method = RequestMethod.GET)
    public String loginFailPage() {
        return "login_fail";
    }

    @RequestMapping(value = "/registration_successful", method = RequestMethod.GET)
    public String registrationSuccessful() {
        return "user/registration_successful";
    }
}
