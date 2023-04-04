package com.application.newsapp;

import android.graphics.drawable.Drawable;

import java.util.Comparator;

/**
 * This will be our news/article object that will hold all information required for a given news article.
 */
public class Article implements Comparable<Article>{

    private Integer ranking;
    private String title;
    private String date;
    private String content;
    private int coverImage;


    public Article(Integer ranking, String title, String date, String content, int coverImage) {
        this.ranking = ranking;
        this.title = title;
        this.date = date;
        this.content = content;
        this.coverImage = coverImage;
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

    public int getCoverImage() {
        return coverImage;
    }

    @Override
    public int compareTo(Article article) {

        // Just a note, the comparable interface will be required for the sort method to work.
        // If the result of the subtraction is greater than 0, that means that this article has a greater ranking compared to the other article.
        // If the result of subtraction is less than 0, that we have the vice versa.
        // Essentially, when we call the sort method, we go from smallest ranking to largest ranking (smallest ranking == higher importance, so a value of 1 means top ranked - hope this isn't too confusing)
        return this.ranking - article.ranking;
    }
}
