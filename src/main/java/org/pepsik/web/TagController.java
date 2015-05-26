package org.pepsik.web;

import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pepsik on 5/26/15.
 */
@Controller
@RequestMapping(value = "/tag")
public class TagController {

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/{tagName}", method = RequestMethod.GET, produces = "text/html")
    public String getTaggedPosts(@PathVariable String tagName, Model model) {
        model.addAttribute(service.getTaggedPosts(tagName));
        return "tag/posts";
    }
}
