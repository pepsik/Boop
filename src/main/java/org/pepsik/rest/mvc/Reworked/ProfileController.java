package org.pepsik.rest.mvc.reworked;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Profile;
import org.pepsik.core.security.AccountUserDetails;
import org.pepsik.core.services.Reworked.ProfileService;
import org.pepsik.rest.resources.CommentListResource;
import org.pepsik.rest.resources.PostListResource;
import org.pepsik.rest.resources.ProfileResource;
import org.pepsik.rest.resources.asm.CommentListResourceAsm;
import org.pepsik.rest.resources.asm.PostListResourceAsm;
import org.pepsik.rest.resources.asm.ProfileResourceAsm;
import org.pepsik.rest.utilities.CommentList;
import org.pepsik.rest.utilities.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pepsik on 10/19/2015.
 */
@Controller
@RequestMapping("/rest/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping("/{username}")
    public ResponseEntity<ProfileResource> getPublicProfile(@PathVariable String username) {
        Profile profile = profileService.findProfileByUsername(username);
        if (profile != null) {
            ProfileResource res = new ProfileResourceAsm().toResource(profile);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping
    public ResponseEntity<ProfileResource> getProfile() {
        String loggedIn = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Profile profile = profileService.findProfileByUsername(loggedIn);
        if (profile != null) {
            ProfileResource res = new ProfileResourceAsm().toResource(profile);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ProfileResource> updateProfile(@RequestBody ProfileResource sentProfile) {
        Profile profile = profileService.updateProfile(sentProfile.toProfile());
        if (profile != null) {
            ProfileResource res = new ProfileResourceAsm().toResource(profile);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{username}/posts", params = "page")
    public ResponseEntity<PostListResource> getPosts(@PathVariable String username, @RequestParam("page") Long page) {
        PostList postList = profileService.getUserPostsByPage(username, page.intValue());
        PostListResource res = new PostListResourceAsm().toResource(postList);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/{username}/comments", params = "page")
    public ResponseEntity<CommentListResource> getComments(@PathVariable String username, @RequestParam("page") Long page) {
        CommentList commentList = profileService.getUserCommentsByPage(username, page.intValue());
        CommentListResource res = new CommentListResourceAsm().toResource(commentList);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
