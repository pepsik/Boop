package org.pepsik.core.persistence.Impl;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.pepsik.core.models.entities.Post;
import org.pepsik.core.models.entities.util.PostLabel;
import org.pepsik.core.persistence.PostDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {

    public static final String SELECT_ALL_POSTS = "SELECT post FROM Post post ORDER BY post.when DESC";

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    @Cacheable(cacheName = "postCache")
    public List<Post> getAllPosts() {
        return em.createQuery(SELECT_ALL_POSTS).getResultList();
    }

    @Override
    @Cacheable(cacheName = "postCache")
    public List<Post> getPostsByPage(int requestedPage, final int DEFAULT_POSTS_PER_PAGE) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> from = criteriaQuery.from(Post.class);
        CriteriaQuery<Post> select = criteriaQuery.orderBy(criteriaBuilder.desc(from.get("when")));

        TypedQuery<Post> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((requestedPage - 1) * DEFAULT_POSTS_PER_PAGE);
        typedQuery.setMaxResults(DEFAULT_POSTS_PER_PAGE);

        return typedQuery.getResultList();
    }

    @Override
    @Cacheable(cacheName = "postCache")
    public long getPostCount() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(Post.class)));
        return em.createQuery(countQuery).getSingleResult();
    }

    @Override
    @TriggersRemove(cacheName = "postCache", removeAll = true)
    public void addPost(Post post) {
        em.persist(post);
    }

    @Override
    @Cacheable(cacheName = "postCache")
    public Post getPostById(long id) {
        return em.find(Post.class, id);
    }

    @Override
    @TriggersRemove(cacheName = "postCache", removeAll = true)
    public void updatePost(Post post) {
        em.merge(post);
    }

    @Override
    @TriggersRemove(cacheName = "postCache", removeAll = true)
    public void deletePost(long id) {
        em.remove(getPostById(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PostLabel> getSimilarPosts(String name) {
        return em.createNativeQuery("SELECT post_id, title FROM posts", "PostLabelResult").getResultList();
    }
}


