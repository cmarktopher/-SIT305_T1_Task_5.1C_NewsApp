package com.application.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // Handler to get articles
    private IArticleHandler articleHandler;

    public HomeFragment() {

        // Required empty public constructor
    }

    public static HomeFragment newInstance() {

        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Get articles through whatever implementation we want.
        // For now, it will be the json based articles.
        articleHandler = new JsonArticleProcessor(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Get core UI elements
        RecyclerView topStoriesRecyclerView = view.findViewById(R.id.topStoriesRecyclerView);
        RecyclerView allStoriesRecyclerView = view.findViewById(R.id.allStoriesRecyclerView);

        // We need to create two separate recycler views. One for top stories and one for all stories.
        // The recycle view implementation will be the same and we can just use two different Array Lists.
        // One array list will contain all articles, and the other will contain the top stories (I'll just do 5 for now based on ranking number)

        // Top stories
        ArticleRecyclerAdapter topStoriesRecycleAdapter = new ArticleRecyclerAdapter(getContext(), articleHandler.GetTopRankedArticles(5));

        // All articles
        ArticleRecyclerAdapter allArticlesRecycleAdapter = new ArticleRecyclerAdapter(getContext(), articleHandler.GetAllArticles());

        // Attach the adapter to the recycler views
        topStoriesRecyclerView.setAdapter(topStoriesRecycleAdapter);
        topStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        allStoriesRecyclerView.setAdapter(allArticlesRecycleAdapter);
        allStoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Inflate the layout for this fragment
        return view;
    }
}