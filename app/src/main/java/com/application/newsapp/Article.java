package com.application.newsapp;

import java.util.Date;

/**
 * This will be our news/article object that will hold all information required for a given news article.
 */
public class Article {

    private Integer ranking;
    private String title;
    private String date;
    private String content;



    public Article(Integer ranking, String title, String date, String content) {
        this.ranking = ranking;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public Integer getRanking() {return ranking;}

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }


}
