package org.pepsik.web;

import org.pepsik.model.User;
import org.pepsik.service.SmartService;
import org.pepsik.web.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

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

          logger.info(service.getFavorites(username).toString());
        model.addAttribute(service.getFavorites(username));
        return "user/favorites";
    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.POST, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public void addFavorite(@PathVariable(value = "postId") String postId) {
        try {
            long id = Long.parseLong(postId);
            if (!service.isExistPost(id))
                throw new ResourceNotFoundException();

            service.addFavorite(id);
        } catch (NumberFormatException exp) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public void removeFavorite(@PathVariable(value = "postId") String postId) {
        try {
            long id = Long.parseLong(postId);
            if (!service.isExistPost(id))
                throw new ResourceNotFoundException();

            service.removeFavorite(id);
        } catch (NumberFormatException exp) {
            throw new ResourceNotFoundException();
        }
    }
}
