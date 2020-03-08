package com.medical.demo.service;

import com.medical.demo.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    void addArticle(Article article);
}
