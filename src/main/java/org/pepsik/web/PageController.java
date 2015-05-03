package org.pepsik.web;

import org.pepsik.model.Comment;
import org.pepsik.service.SmartService;
import org.pepsik.web.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    private SmartService service;

    @RequestMapping
    public String getPage(@PathVariable("pageId") int pageId, Model model) {
        if (pageId > service.getPagesCount()) {
            logger.info(Long.toString(service.getPagesCount()));
            throw new ResourceNotFoundException();
        }

        model.addAttribute(service.getPostsByPage(pageId));
        model.addAttribute("pagination", service.getPagination(pageId));
        model.addAttribute("currentPageIndex", pageId);
        return "home";
    }
}
