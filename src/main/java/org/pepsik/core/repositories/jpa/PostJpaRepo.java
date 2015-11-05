package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Post;
import org.pepsik.core.repositories.PostRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by pepsik on 9/29/2015.
 */
@Repository
public class PostJpaRepo implements PostRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Post create(Post data) {
        em.persist(data);
        return data;
    }

    @Override
    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    @Override
    public List<Post> findAll() {
        Query query = em.createQuery("SELECT a FROM Post a order by a.when desc");
        return query.getResultList();
    }

    @Override
    public Post update(Long id, Post data) {
        Post post = em.find(Post.class, id);
        if (post != null) {
            post.setText(data.getText());
            post.setTitle(data.getTitle());
            post.setTags(data.getTags());
        }
        return post;
    }

    @Override
    public Post delete(Long id) {
        Post post = em.find(Post.class, id);
        if (post != null) {
            em.remove(post);
        }
        return post;
    }

    @Override
    public List<Post> getPostsByPage(Integer requestedPage, Integer postsPerPage) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> from = criteriaQuery.from(Post.class);
        CriteriaQuery<Post> select = criteriaQuery.orderBy(criteriaBuilder.desc(from.get("when")));
        TypedQuery<Post> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((requestedPage - 1) * postsPerPage);
        typedQuery.setMaxResults(postsPerPage);
        return typedQuery.getResultList();
    }

    @Override
    public Long getPostCount() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(Post.class)));
        return em.createQuery(countQuery).getSingleResult();
    }
}
