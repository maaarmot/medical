package com.medical.demo.service;

import com.medical.demo.model.Article;
import com.medical.demo.base.result.Results;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();

    Results addArticle(Article article);

    Article getArticleById(Integer id);
}
