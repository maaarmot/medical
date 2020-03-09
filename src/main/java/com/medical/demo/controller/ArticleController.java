package com.medical.demo.controller;

import com.medical.demo.base.result.ResponseCode;
import com.medical.demo.base.result.Results;
import com.medical.demo.model.Article;
import com.medical.demo.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/art")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public String getAllArticles(Model model){
        List<Article> articleList = articleService.getAllArticles();
        model.addAttribute("articleList",articleList);
        return "art/art-list";
    }

    @GetMapping("/add")
    public String publish(Model model){
        model.addAttribute(new Article());
        return "art/art-add";
    }

    @PostMapping("/add")
    @ResponseBody
    public Results<Article> doPublish(@RequestParam("title") String title,
                                      @RequestParam("description") String description,
//                            @RequestParam("id") Long id,
                                      HttpServletRequest request,
                                      Model model){
        //输入框不能为空的提示应出现在用户未登录的提示后面
        model.addAttribute("title",title);
        model.addAttribute("description",description);

        if(title==null||title.equals("")){
            model.addAttribute("error","文章标题不能为空");
            return Results.failure(ResponseCode.PARAMETER_MISSING.getCode(),ResponseCode.PARAMETER_MISSING.getMessage());
        }
        if(description==null||description.equals("")){
            model.addAttribute("error","文章内容不能为空");
            return Results.failure(ResponseCode.PARAMETER_MISSING.getCode(),ResponseCode.PARAMETER_MISSING.getMessage());
        }

        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
////        article.setId(id);
//        articleService.addArticle(article);
        return articleService.addArticle(article);
//        return "art/art-list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(name="id")Integer id,Model model){
        Article article=articleService.getArticleById(id);
        model.addAttribute("article",article);
        return "art/art-detail";
    }

}
