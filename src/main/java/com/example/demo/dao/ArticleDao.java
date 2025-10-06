package com.example.demo.dao;

import com.example.demo.model.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleDao {
    List<Article> findAll();
    Optional<Article> findById(Long id);
    Article save(Article a);
    Article update(Article a);
    void deleteById(Long id);
}
