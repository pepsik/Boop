package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Account;
import org.pepsik.core.models.entities.Comment;
import org.pepsik.core.models.entities.Post;
import org.pepsik.core.models.entities.Profile;
import org.pepsik.core.repositories.ProfileRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by pepsik on 10/19/2015.
 */
@Repository
public class ProfileJpaRepo implements ProfileRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Profile create(Profile data) {
        em.persist(data);
        return data;
    }

    @Override
    public Profile findById(Long id) {
        return em.find(Profile.class, id);
    }

    @Override
    public Profile update(Long id, Profile data) {
        Profile profile = em.find(Profile.class, id);
        if (profile != null) {
            profile.setAbout(data.getAbout());
            profile.setBirthdate(data.getBirthdate());
            profile.setCountry(data.getCountry());
            profile.setCity(data.getCity());
            profile.setEmail(data.getEmail());
            profile.setFirstname(data.getFirstname());
            profile.setGender(data.getGender());
            profile.setJob(data.getJob());
            profile.setLastname(data.getLastname());
        }
        return profile;
    }

    @Override
    public Profile delete(Long id) {
        Profile profile = em.find(Profile.class, id);
        if (profile != null) {
            em.remove(profile);
        }
        return profile;
    }

    @Override
    public List<Post> getPosts(Account account, Integer requestedPage, Integer postsPerPage) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Post> q = cb.createQuery(Post.class);
        Root<Post> from = q.from(Post.class);
        CriteriaQuery<Post> select = q.select(from).where(cb.equal(from.get("owner").get("id"), account.getId()))
                .orderBy(cb.desc(from.get("when")));
        TypedQuery<Post> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((requestedPage - 1) * postsPerPage);
        typedQuery.setMaxResults(postsPerPage);
        return typedQuery.getResultList();
    }

    @Override
    public Long getPostCount(Account account) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Post> from = countQuery.from(Post.class);
        countQuery.select(cb.count(from)).where(cb.equal(from.get("owner").get("id"), account.getId()));
        return em.createQuery(countQuery).getSingleResult();
    }

    @Override
    public List<Comment> getComments(Account account, Integer requestedPage, Integer commentsPerPage) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Comment> q = cb.createQuery(Comment.class);
        Root<Comment> from = q.from(Comment.class);
        CriteriaQuery<Comment> select = q.select(from).where(cb.equal(from.get("owner").get("id"), account.getId()))
                .orderBy(cb.desc(from.get("when")));
        TypedQuery<Comment> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((requestedPage - 1) * commentsPerPage);
        typedQuery.setMaxResults(commentsPerPage);
        return typedQuery.getResultList();
    }

    @Override
    public Long getCommentCount(Account account) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Comment> from = countQuery.from(Comment.class);
        countQuery.select(cb.count(from)).where(cb.equal(from.get("owner").get("id"), account.getId()));
        return em.createQuery(countQuery).getSingleResult();
    }
}
