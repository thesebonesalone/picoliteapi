package com.picolite.controllers;


import com.picolite.fakedb.FakeDB;
import com.picolite.models.Article;
import com.picolite.models.ArticleContainer;
import com.picolite.tools.FancyText;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {


    @CrossOrigin
    @RequestMapping(value = "/articles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> getArticle(@PathVariable("id") Long articleId)
    {
        System.out.println("Retrieving article " + articleId);

        Article a = FakeDB.fetchArticle(articleId);

        //punch up content
        a.setContent(FancyText.convert(a.getContent()));

        return new ResponseEntity<Article>(a, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleContainer> getAllArticles()
    {
        System.out.println("Fetching all articles");

        ArticleContainer articleContainer = new ArticleContainer();

        articleContainer.setArticles(FakeDB.allArticles());

        return new ResponseEntity<ArticleContainer>(articleContainer, HttpStatus.OK);
    }

}
