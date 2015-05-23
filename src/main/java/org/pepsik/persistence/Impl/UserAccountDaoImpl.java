package org.pepsik.persistence.Impl;

import org.pepsik.model.User;
import org.pepsik.persistence.UserAccountDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by pepsik on 5/23/15.
 */

@Repository
public class UserAccountDaoImpl implements UserAccountDao {

    public static final String SELECT_USER_BY_USERNAME = "SELECT user FROM User user WHERE user.username=:username";
    public static final String INSERT_USER_AUTHORITY = "INSERT INTO users_authority (user_id_fk, role_id_fk)  values (:id , 2)";


    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        return (User) em.createQuery(SELECT_USER_BY_USERNAME).setParameter("username", username).getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public void setUserAuthority(User user) {
        em.createNativeQuery(INSERT_USER_AUTHORITY).setParameter("id", user.getId()).executeUpdate();
    }
}
