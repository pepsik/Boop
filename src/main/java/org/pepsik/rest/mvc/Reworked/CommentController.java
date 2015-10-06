package org.pepsik.rest.mvc.Reworked;

import org.pepsik.core.models.entities.Reworked.Comment;
import org.pepsik.core.services.Reworked.CommentService;
import org.pepsik.rest.resources.CommentListResource;
import org.pepsik.rest.resources.CommentResource;
import org.pepsik.rest.resources.asm.CommentListResourceAsm;
import org.pepsik.rest.resources.asm.CommentResourceAsm;
import org.pepsik.rest.utilities.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pepsik on 10/3/2015.
 */

@Controller
@RequestMapping(value = "/rest/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommentResource> createComment(@PathVariable Long postId, @RequestBody CommentResource sentComment) {
        Comment createdComment = commentService.createComment(postId, "username3", sentComment.toComment());
        CommentResource res = new CommentResourceAsm().toResource(createdComment);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<CommentListResource> getCommentsByPost(@PathVariable Long postId) {
        CommentList commentList = commentService.findAllCommentsByPost(postId);
        if (commentList.getComments() != null) {
            CommentListResource res = new CommentListResourceAsm().toResource(commentList);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    public ResponseEntity<CommentResource> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentResource sentComment) {
        Comment updatedComment = commentService.updateComment(commentId, sentComment.toComment());
        if (updatedComment != null) {
            CommentResource res = new CommentResourceAsm().toResource(updatedComment);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<CommentResource> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        Comment comment = commentService.deleteComment(commentId);
        if (comment != null) {
            CommentResource res = new CommentResourceAsm().toResource(comment);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
