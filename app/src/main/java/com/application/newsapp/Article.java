package com.application.newsapp;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Comparator;

/**
 * This will be our news/article object that will hold all information required for a given news article.
 */
public class Article implements Comparable<Article>, Parcelable {

    private Integer id;
    private Integer ranking;
    private String title;
    private String date;
    private String content;
    private int coverImage;
    private int[] relatedArticleIds;


    public Article(Integer id, Integer ranking, String title, String date, String content, int coverImage, int[] relatedArticleIds) {
        this.id = id;
        this.ranking = ranking;
        this.title = title;
        this.date = date;
        this.content = content;
        this.coverImage = coverImage;
        this.relatedArticleIds = relatedArticleIds;
    }

    protected Article(Parcel in) {
        if (in.readByte() == 0) {
            ranking = null;
        } else {
            ranking = in.readInt();
        }
        title = in.readString();
        date = in.readString();
        content = in.readString();
        coverImage = in.readInt();
        in.readIntArray(relatedArticleIds);
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

    /**
     * The following methods are required for the Comparable interface
     */

    @Override
    public int compareTo(Article article) {

        // Just a note, the comparable interface will be required for the sort method to work.
        // If the result of the subtraction is greater than 0, that means that this article has a greater ranking compared to the other article.
        // If the result of subtraction is less than 0, that we have the vice versa.
        // Essentially, when we call the sort method, we go from smallest ranking to largest ranking (smallest ranking == higher importance, so a value of 1 means top ranked - hope this isn't too confusing)
        return this.ranking - article.ranking;
    }

    /**
     * The following methods are required for the Parcelable interface
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        // Looks like android studio auto-populated everything for me.
        if (ranking == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(ranking);
        }
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(content);
        parcel.writeInt(coverImage);
        parcel.writeIntArray(relatedArticleIds);
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public int[] getRelatedArticleIds() {
        return relatedArticleIds;
    }
}
