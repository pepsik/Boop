package org.pepsik.rest.mvc;

import org.pepsik.core.model.Post;
import org.pepsik.core.service.SmartService;
import org.pepsik.rest.exception.BadRequestException;
import org.pepsik.rest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pepsik on 4/23/15.
 */

@RestController
@RequestMapping(method = RequestMethod.GET)
public class PageController {

    @Autowired
    private SmartService service;

    @RequestMapping(value = "/api/page/{pageId}")
    public List<Post> getPage(@PathVariable("pageId") String strPageId, Model model) {
        int pageId;
        try {
            pageId = Integer.parseInt(strPageId);
        } catch (NumberFormatException ex) {
            throw new BadRequestException();
        }
//        model.addAttribute("currentPageIndex", pageId);
        return service.getPostsByPage(pageId);
    }

    @RequestMapping(value = "/api/pagination", params = {"activePage"})
    public List<String> getPagination(@RequestParam(value = "activePage") int pageId) {
        long postsCount = service.getAllPostsCount();
        if (pageId > service.getPagesCount(postsCount) || pageId <= 0)
            throw new ResourceNotFoundException();
        return service.getPagination(pageId, postsCount);
    }
}
