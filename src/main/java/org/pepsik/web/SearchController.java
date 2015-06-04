package org.pepsik.web;

import org.pepsik.model.Post;
import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by pepsik on 5/16/15.
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/posts", method = RequestMethod.POST, produces = "text/html")
    public String postSearch(@RequestParam String name, Model model) {
        List<Post> result = service.getSimilarPosts(name);
        model.addAttribute(result);
        model.addAttribute("resultSize", result.size());
        return "search";
    }
}
