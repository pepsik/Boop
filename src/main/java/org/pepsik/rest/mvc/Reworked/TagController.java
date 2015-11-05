package org.pepsik.rest.mvc.reworked;

import org.pepsik.core.models.entities.Reworked.Tag;
import org.pepsik.core.services.Reworked.TagService;
import org.pepsik.rest.exceptions.BadRequestException;
import org.pepsik.rest.resources.PostListResource;
import org.pepsik.rest.resources.TagResource;
import org.pepsik.rest.resources.asm.PostListResourceAsm;
import org.pepsik.rest.resources.asm.TagResourceAsm;
import org.pepsik.rest.utilities.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by pepsik on 10/27/2015.
 */
@Controller
@RequestMapping("/rest/tag/{name}")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/posts")
    public ResponseEntity<PostListResource> getPostsByTag(@PathVariable String name,
                                                          @RequestParam(value = "page", required = false) Long page) {
        try {
            String result = URLDecoder.decode(name, "UTF-8");
            PostList posts = tagService.getPostsByTag(result);
            PostListResource res = new PostListResourceAsm().toResource(posts);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping
    public ResponseEntity<TagResource> getTag(@PathVariable String name) {
        try {
            String result = URLDecoder.decode(name, "UTF-8");
            Tag tag = tagService.findTag(result);
            if (tag != null) {
                TagResource res = new TagResourceAsm().toResource(tag);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        } catch (UnsupportedEncodingException e) {/*nothing to do*/ }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<TagResource> updateTag(@PathVariable String name, TagResource sentTag) {
        try {
            String result = URLDecoder.decode(name, "UTF-8");
            Tag updatedTag = tagService.updateTag(result, sentTag.toTag());
            if (updatedTag != null) {
                TagResource res = new TagResourceAsm().toResource(updatedTag);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        } catch (UnsupportedEncodingException e) {/*nothing to do*/ }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
