package org.pepsik.web;

import org.pepsik.service.SmartService;
import org.pepsik.web.exception.BadRequestException;
import org.pepsik.web.exception.ResourceNotFoundException;
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
    public String getPage(@PathVariable("pageId") String strPageId, Model model) {
        int pageId;
        try {
            pageId = Integer.parseInt(strPageId);
        } catch (NumberFormatException ex) {
            throw new BadRequestException();
        }

        long postsCount = service.getAllPostsCount();
        if (pageId > service.getPagesCount(postsCount) || pageId <= 0)
            throw new ResourceNotFoundException();

        model.addAttribute(service.getPostsByPage(pageId));
        model.addAttribute("pagination", service.getPagination(pageId, postsCount));
        model.addAttribute("currentPageIndex", pageId);
        return "home";
    }
}
