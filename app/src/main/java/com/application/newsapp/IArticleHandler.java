package com.application.newsapp;

import android.content.Context;
import android.os.Parcelable;

import java.util.ArrayList;

public interface IArticleHandler{

    ArrayList<Article> GetAllArticles();
    ArrayList<Article> GetTopRankedArticles(Integer numberOfArticles);
}
