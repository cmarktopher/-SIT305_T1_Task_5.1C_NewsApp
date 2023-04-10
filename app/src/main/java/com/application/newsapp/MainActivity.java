package com.application.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.application.newsapp.DataAccess.IArticleDataAccess;
import com.application.newsapp.DataAccess.JsonArticleDataAccess;
import com.application.newsapp.Fragments.HomeFragment;
import com.application.newsapp.ViewModels.ArticleDataAccessViewModel;

/**
 * Just a couple of notes about this entire application.
 * Since we are required to have a top stories section and an all news section.
 * I will use a simple ranking system where each article will be ranked and only the top 5-6 articles will be in the top stories.
 * The news section will simply contain all articles
 * The task sheet does seem to lists new agencies under the all news but I assume this is a mistake and actual news articles are meant to go there.
 * It didn't make much sense to me to have news agencies listed (I assume each article would belong to a news agency perhaps)
 * Also here are some links to sources I used to learn how to do all of this:
 * Recycler View: https://www.youtube.com/watch?v=Mc0XT58A1Z4
 * Loading and reading json: https://www.tutorialspoint.com/how-to-read-a-file-from-assets-on-android
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We need a data access object
        IArticleDataAccess dataAccess = new JsonArticleDataAccess(getApplicationContext());

        // We want to pass in our data access object to our view model so that it can be shared across all fragments
        // The good thing here is that if I ever change the data access object, I can simply make one change here, and not change anything else (well, ideally).
        // There is probably a better way of using the view model, but sticking with a simple approach for now (I don't think there will be any issues doing it this way)
        ArticleDataAccessViewModel articleViewModel = new ViewModelProvider(this).get(ArticleDataAccessViewModel.class);
        articleViewModel.setDataAccess(dataAccess);

        // Now, lets move into the fragment.
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.fade_out)
                .setReorderingAllowed(true)
                .replace(R.id.newsAppFragmentContainer, HomeFragment.newInstance(), null)
                .addToBackStack(null)
                .commit();
    }
}