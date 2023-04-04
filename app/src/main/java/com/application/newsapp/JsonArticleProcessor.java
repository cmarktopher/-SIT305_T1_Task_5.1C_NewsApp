package com.application.newsapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class JsonArticleProcessor implements IArticleHandler {

    private ArrayList<Article> newsArticles = new ArrayList<>();

    public JsonArticleProcessor(Context context) {

        String articleData = "";

        // Get our json file containing articles and place it into a string
        try {
            // Load the json file
            InputStream dataStream =  context.getAssets().open("NewsArticles.json");

            // Create a buffer for the reader
            Integer dataStreamSize = dataStream.available();
            byte[] dataBuffer = new byte[dataStreamSize];

            // Read the data and place into the buffer
            dataStream.read(dataBuffer);

            // Create a new string with the data from the buffer
            articleData = new String(dataBuffer);

            // Utilize the JSONArray object to get each json article as an json object and then populate new Article classes.
            JSONArray articlesAsJson = new JSONArray(articleData);
            Integer articlesAsJsonStringLength = articlesAsJson.length();

            for (int i = 0; i < articlesAsJsonStringLength; i++) {

                // Slight note, I am currently using a placeholder vector image
                // So, this information won't be through the json.
                // Probably not a good way to do this but this is just for testing and the task.
                // Future implementations of this would probably use a database or some kind of news API

                JSONObject articleAsJson = articlesAsJson.getJSONObject(i);
                newsArticles.add(

                        new Article(
                                articleAsJson.getInt("ranking"),
                                articleAsJson.getString("title"),
                                articleAsJson.getString("date"),
                                articleAsJson.getString("content"),
                                R.drawable.baseline_article_24
                        )
                );
            }

            // We might as well sort the articles here based on ranking
            Collections.sort(newsArticles);


        } catch (IOException e) {

            throw new RuntimeException(e);

        } catch (JSONException e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<Article> GetAllArticles() {

        return newsArticles;
    }

    @Override
    public ArrayList<Article> GetTopRankedArticles(Integer numberOfArticles) {

        ArrayList<Article> topArticles = new ArrayList<>(numberOfArticles);

        // Probably a better way to do this but I'll simply loop through the number of articles we required based on the parameter and add them to the new collection.
        // These articles are also already sorted with higher ranking articles appearing at the front of the collection.
        for (int i = 0; i < numberOfArticles; i++) {

            topArticles.add(newsArticles.get(i));

        }

        return topArticles;
    }
}
