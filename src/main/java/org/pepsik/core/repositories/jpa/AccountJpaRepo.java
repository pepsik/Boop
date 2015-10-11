package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.repositories.AccountRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

/**
 * Created by pepsik on 9/29/2015.
 */
@Repository
public class AccountJpaRepo implements AccountRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Account create(Account data) {
        em.persist(data);
        return data;
    }

    @Override
    public Account findById(Long id) {
        return em.find(Account.class, id);
    }

    @Override
    public Account findByUsername(String username) {
        Query query = em.createQuery("SELECT a FROM Account a WHERE a.username=?1");
        query.setParameter(1, username);
        List<Account> accounts = query.getResultList();
        if(accounts.size() == 0) {
            return null;
        } else {
            return accounts.get(0);
        }
    }

    @Override
    public List<Account> findAll() {
        Query query = em.createQuery("SELECT a FROM Account a");
        return query.getResultList();
    }

    @Override
    public Account update(Account data) {
        return null;
    }

    @Override
    public Account delete(Account data) {
        return null;
    }
}
