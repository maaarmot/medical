package com.medical.demo.service.impl;

import com.medical.demo.mapper.ArticleMapper;
import com.medical.demo.model.Article;
import com.medical.demo.model.ArticleExample;
import com.medical.demo.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getAllArticles() {
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("update_time desc");
        return articleMapper.selectByExample(example);
    }

    @Override
    public void addArticle(Article article) {
        articleMapper.insert(article);
    }
}
