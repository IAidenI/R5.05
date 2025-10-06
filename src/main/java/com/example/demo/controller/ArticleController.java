package com.example.demo.controller;

import com.example.demo.dao.ArticleDao;
import com.example.demo.model.Article;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleDao dao;

    public ArticleController(ArticleDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Article> getAll() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public Article getOne(@PathVariable Long id) {
        return dao.findById(id).orElseThrow();
    }

    record CreateReq(@NotBlank String author, @NotBlank String content) {}
    record UpdateReq(@NotBlank String content) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article create(@Valid @RequestBody CreateReq req) {
        Article a = new Article();
        a.setAuthor(req.author());
        a.setContent(req.content());
        return dao.save(a);
    }

    @PutMapping("/{id}")
    public Article update(@PathVariable Long id, @Valid @RequestBody UpdateReq req) {
        Article a = dao.findById(id).orElseThrow();
        a.setContent(req.content());
        return dao.update(a);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }
}
