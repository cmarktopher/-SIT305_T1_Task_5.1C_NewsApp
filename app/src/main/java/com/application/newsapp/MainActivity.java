package com.application.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Just a couple of notes about this entire application.
 * Since we are required to have a top stories section and an all news section.
 * I will use a simple ranking system where each article will be ranked and only the top 5-6 articles will be in the top stories.
 * The all articles section will simply contain all the other articles
 * The task sheet does seem to lists new agencies under the all news but I assume this is a mistake and actual news articles are meant to go there.
 * It didn't make much sense to me to have news agencies listed (I assume each article would belong to a news agency perhaps)
 * Also here are some links to sources I used to learn how to do all of this:
 * Recycler View: https://www.youtube.com/watch?v=Mc0XT58A1Z4
 * Loading and reading json: https://www.tutorialspoint.com/how-to-read-a-file-from-assets-on-android
 */
public class MainActivity extends AppCompatActivity {

    // Handler to get articles
    private IArticleHandler articleHandler;

    // Create a list of news articles that will be utilized throughout the application.
    private ArrayList<Article> newsArticles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Get articles through whatever implementation we want.
        // For now, it will be the json based articles.
        articleHandler = new JsonArticleProcessor();
        newsArticles = articleHandler.GetAllArticles(this);

        LinearLayout layout = findViewById(R.id.testVerticalLayout);

        for (int i = 0; i < newsArticles.size(); i++) {

            Button button = new Button(this);
            button.setText(newsArticles.get(i).getTitle());
            layout.addView(button);
        }
    }


}