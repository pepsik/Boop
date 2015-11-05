package org.pepsik.core.services.Impl;

import org.pepsik.core.models.entities.Account;
import org.pepsik.core.models.entities.Comment;
import org.pepsik.core.repositories.jpa.AccountJpaRepo;
import org.pepsik.core.repositories.jpa.CommentJpaRepo;
import org.pepsik.core.services.CommentService;
import org.pepsik.rest.utilities.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Comment createComment(Long postId, Comment data) {
        String loggedIn = SecurityContextHolder.getContext().getAuthentication().getName();
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
    @PreAuthorize("hasRole('ROLE_USER') and @securityService.canUpdateComment(#commentId)")
    public Comment updateComment(Long commentId, Long postId, Comment data) {
        return commentJpaRepo.update(commentId, postId, data);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER') and @securityService.canDeleteComment(#commentId)")
    public Comment deleteComment(Long commentId, Long postId) {
        return commentJpaRepo.delete(commentId, postId);
    }
}
