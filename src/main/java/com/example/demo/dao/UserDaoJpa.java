package com.example.demo.dao;

import com.example.demo.model.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class UserDaoJpa implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<User> findByUsername(String username) {
        return em.createQuery("select user from User user where user.username = :username", User.class)
                 .setParameter("username", username)
                 .getResultStream()
                 .findFirst();
    }

    @Override
    @Transactional
    public User save(User u) {
        em.persist(u);
        return u;
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select user from User user order by user.id", User.class)
                 .getResultList();
    }
}
