package com.example.demo.dao;

import com.example.demo.model.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticleDaoJpa implements ArticleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Article> findAll() {
        return em.createQuery("select a from Article a order by a.id", Article.class).getResultList();
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(em.find(Article.class, id));
    }

    @Override
    @Transactional
    public Article save(Article a) {
        em.persist(a);
        return a;
    }

    @Override
    @Transactional
    public Article update(Article a) {
        return em.merge(a);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Article ref = em.find(Article.class, id);
        if (ref != null) em.remove(ref);
    }
}
