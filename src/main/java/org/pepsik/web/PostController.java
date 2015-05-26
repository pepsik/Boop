package org.pepsik.web;

import org.joda.time.DateTime;
import org.pepsik.model.Post;
import org.pepsik.model.Tag;
import org.pepsik.service.SmartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.*;

/**
 * Created by pepsik on 4/9/15.
 */

@Controller
@RequestMapping("/post")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private SmartService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text.equals(""))
                    return;

                List<String> tagsList = Arrays.asList(text.split(","));
                Set<Tag> tags = new HashSet<>();
                for (String stringTag : tagsList) {
                    Tag tag = new Tag();
                    tag.setName(stringTag);
                    tags.add(tag);
                }
                setValue(tags);
            }

            @Override
            public String getAsText() {
                Set<Tag> tags = (Set<Tag>) getValue();
                String stringTags = "";

                if (tags == null || tags.size() == 0)
                    return stringTags;

                Iterator<Tag> iterator = tags.iterator();
                stringTags += iterator.next().getName();
                while (iterator.hasNext())
                    stringTags += "," + iterator.next().getName();
                return stringTags;
            }
        });
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") long id, HttpSession session, Model model) {
        Post post = service.getPost(id);
        session.setAttribute("post", post);
        model.addAttribute(post);
        return "post/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createPost(@Valid Post post, BindingResult result) {

        if (result.hasErrors())
            return "post/create";

        post.setWhen(new DateTime());
        service.savePost(post);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPost(@PathVariable("id") long id, Model model) {
        model.addAttribute(service.getPost(id));
        return "post/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updatePost(@PathVariable("id") long id, @Valid @ModelAttribute("post") Post updatedPost, BindingResult result, HttpSession session) {

        if (result.hasErrors())
            return "post/edit";

        final Post post = (Post) session.getAttribute("post");
        updatedPost.setId(post.getId());
        updatedPost.setWhen(post.getWhen());
        updatedPost.setUser(post.getUser());
        updatedPost.setComments(post.getComments());
        service.savePost(updatedPost);
        session.removeAttribute("post");
        return "redirect:/post/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePost(@PathVariable("id") long id) {
        service.deletePost(id);
        return "redirect:/home";
    }
}
