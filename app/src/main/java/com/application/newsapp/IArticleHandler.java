package com.application.newsapp;

import android.content.Context;

import java.util.ArrayList;

public interface IArticleHandler {

    ArrayList<Article> GetAllArticles();
    ArrayList<Article> GetTopRankedArticles(Integer numberOfArticles);
}
