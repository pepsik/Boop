package org.pepsik.rest.mvc;

import org.pepsik.core.service.SmartService;
import org.pepsik.rest.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
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
