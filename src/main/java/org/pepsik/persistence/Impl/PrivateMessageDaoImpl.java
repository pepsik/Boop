package org.pepsik.persistence.Impl;

import org.pepsik.model.Favorite;
import org.pepsik.model.PrivateMessage;
import org.pepsik.model.User;
import org.pepsik.persistence.PrivateMessageDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by pepsik on 6/2/15.
 */

@Repository
public class PrivateMessageDaoImpl implements PrivateMessageDao {

    public static final String SELECT_USER_OUTPUT_PRIVATE_MESSAGES = "SELECT pm from PrivateMessage pm " +
            "where pm.sender.id = :id order by pm.dispatchDate desc";
    public static final String SELECT_USER_INPUT_PRIVATE_MESSAGES = "SELECT pm from PrivateMessage pm " +
            "where pm.recipient.id = :id order by pm.dispatchDate desc";
    @PersistenceContext
    private EntityManager em;

    @Override
    public void createPrivateMessage(PrivateMessage message) {
        em.persist(message);
    }

    @Override
    public PrivateMessage getPrivateMessage(long id) {
        return em.find(PrivateMessage.class, id);
    }

    @Override
    public List<PrivateMessage> getOutputPrivateMessages(long userId) {
        return em.createQuery(SELECT_USER_OUTPUT_PRIVATE_MESSAGES).setParameter("id", userId).getResultList();
    }

    @Override
    public List<PrivateMessage> getInputPrivateMessages(long userId) {
        return em.createQuery(SELECT_USER_INPUT_PRIVATE_MESSAGES).setParameter("id", userId).getResultList();
    }

    @Override
    public void updatePrivateMessage(PrivateMessage message) {
        em.merge(message);
    }

    @Override
    public void deletePrivateMessage(PrivateMessage message) {
        em.remove(message);
    }

    @Override
    public long getInputPrivateMessageCount(User user) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<PrivateMessage> from = countQuery.from(PrivateMessage.class);
        countQuery.select(criteriaBuilder.count(from)).where(criteriaBuilder.equal(from.get("recipient").get("id"), user.getId()));
        return em.createQuery(countQuery).getSingleResult();
    }

    @Override
    public long getOutputPrivateMessageCount(User user) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<PrivateMessage> from = countQuery.from(PrivateMessage.class);
        countQuery.select(criteriaBuilder.count(from)).where(criteriaBuilder.equal(from.get("sender").get("id"), user.getId()));
        return em.createQuery(countQuery).getSingleResult();
    }
}
