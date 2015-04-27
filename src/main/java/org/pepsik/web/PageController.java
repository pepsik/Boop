package org.pepsik.web;

import org.pepsik.model.Post;
import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pepsik on 4/23/15.
 */

@Controller
@RequestMapping(value = "/page/{pageId}", method = RequestMethod.GET)
public class PageController {

    @Autowired
    private SmartService service;

    @RequestMapping
    public String getPage(@PathVariable("pageId") int pageId, Model model) {
        model.addAttribute(service.getThreadsByPage(pageId));
        model.addAttribute("pagination", service.getPagination(pageId));
        model.addAttribute("currentPageIndex", pageId);
        model.addAttribute(new Post());
        return "home";
    }
}
