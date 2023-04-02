package com.application.newsapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonArticleProcessor implements IArticleHandler {


    @Override
    public ArrayList<Article> GetAllArticles(Context context) {

        ArrayList<Article> newsArticles = new ArrayList<>();

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

                JSONObject articleAsJson = articlesAsJson.getJSONObject(i);
                newsArticles.add(

                        new Article(
                                articleAsJson.getInt("ranking"),
                                articleAsJson.getString("title"),
                                articleAsJson.getString("date"),
                                articleAsJson.getString("content")
                        )
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(e);

        } catch (JSONException e) {

            throw new RuntimeException(e);
        }

        return newsArticles;
    }
}
