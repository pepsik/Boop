package org.pepsik.rest.mvc;

import org.pepsik.core.model.Tag;
import org.pepsik.core.service.SmartService;
import org.pepsik.rest.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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

    @RequestMapping(value = "/{tagName}/edit", method = RequestMethod.GET, produces = "text/html")
    public String editTag(@PathVariable String tagName, Model model, HttpSession session) {
        Tag tag = service.getTag(tagName);
        session.setAttribute("tag", tag);
        model.addAttribute(tag);
        return "tag/edit";
    }

    @RequestMapping(value = "/{tagName}", method = RequestMethod.PUT, produces = "text/html")
    public String updateTag(@Valid Tag updatedTag, BindingResult result, @PathVariable String tagName, HttpSession session) {
        if (result.hasErrors())
            return "tag/edit";

        final Tag tag = (Tag) session.getAttribute("tag");
        updatedTag.setId(tag.getId());
        updatedTag.setCreateDate(tag.getCreateDate());
        updatedTag.setPostsCount(tag.getPostsCount());
        updatedTag.setAuthor(tag.getAuthor());
        service.saveTag(updatedTag);
        try {
            tagName = URLEncoder.encode(tagName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BadRequestException();
        }
        System.out.println(tagName);
        return "redirect:/tag/" + tagName;
    }
}
