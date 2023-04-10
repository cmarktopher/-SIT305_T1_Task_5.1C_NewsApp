package com.application.newsapp.DataAccess;

import com.application.newsapp.DataModels.Article;

import java.util.ArrayList;

public interface IArticleDataAccess {

    ArrayList<Article> GetAllArticles();
    ArrayList<Article> GetTopRankedArticles(Integer numberOfArticles);
}
