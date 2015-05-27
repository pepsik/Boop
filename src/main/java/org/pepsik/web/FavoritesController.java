package org.pepsik.web;

import org.pepsik.service.SmartService;
import org.pepsik.web.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pepsik on 5/22/15.
 */

@Controller
@RequestMapping(value = "/user/{username}/favorite")
public class FavoritesController {

    private static final Logger logger = LoggerFactory.getLogger(FavoritesController.class);

    @Autowired
    private SmartService service;

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public String getUserFavorites(@PathVariable String username, Model model) {
        if (!service.isExistUsername(username))
            throw new ResourceNotFoundException();

        model.addAttribute(service.getFavorites(username));
        return "user/favorites";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public void addFavorite(@RequestBody String postId) {
        try {
            long id = Long.parseLong(postId);
            service.saveFavorite(id);
        } catch (NumberFormatException exp) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removeFavorite(@RequestBody String postId) {
        try {
            long id = Long.parseLong(postId, 10);
            service.removeFavorite(id);
        } catch (NumberFormatException exp) {
            throw new ResourceNotFoundException();
        }
    }
}
