package org.pepsik.core.services.Reworked.Impl;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Comment;
import org.pepsik.core.repositories.jpa.AccountJpaRepo;
import org.pepsik.core.repositories.jpa.CommentJpaRepo;
import org.pepsik.core.services.Reworked.CommentService;
import org.pepsik.rest.utilities.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pepsik on 10/3/2015.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentJpaRepo commentJpaRepo;

    @Autowired
    private AccountJpaRepo accountJpaRepo;

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Comment createComment(Long postId, String loggedIn, Comment data) {
        Account author = accountJpaRepo.findByUsername(loggedIn);
        data.setOwner(author);
        return commentJpaRepo.create(postId, data);
    }

    @Override
    public CommentList findAllCommentsByPost(Long postId) {
        return new CommentList(commentJpaRepo.findCommentsByPost(postId));
    }

    @Override
    public Comment findCommentById(Long id) {
        return commentJpaRepo.findById(id);
    }

    @Override
    @PreAuthorize("(principal.username == this.findCommentById(#commentId).getOwner().getUsername()) or hasRole('ROLE_ADMIN')")
    public Comment updateComment(Long commentId, Comment data) {
        return commentJpaRepo.update(commentId, data);
    }

    @Override
    @PreAuthorize("(principal.username == this.findCommentById(#commentId).getOwner().getUsername()) or hasRole('ROLE_ADMIN')")
    public Comment deleteComment(Long commentId) {
        return commentJpaRepo.delete(commentId);
    }
}
