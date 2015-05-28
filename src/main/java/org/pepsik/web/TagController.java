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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
        //for support Cirillyc url chars add URIEncoding="UTF-8" to <Connector > in server.xml Tomcat confs
        try {
            String result = URLDecoder.decode(tagName, "UTF-8");
            model.addAttribute(service.getTag(result));
        } catch (UnsupportedEncodingException e) {
            throw new BadRequestException();
        }

        return "tag/posts";
    }
}
