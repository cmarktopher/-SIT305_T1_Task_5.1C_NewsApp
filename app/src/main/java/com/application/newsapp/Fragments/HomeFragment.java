package com.application.newsapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.newsapp.RecyclerAdapters.ArticleRecyclerAdapter;
import com.application.newsapp.DataAccess.IArticleDataAccess;
import com.application.newsapp.R;
import com.application.newsapp.ViewModels.ArticleDataAccessViewModel;

/**
 * This activity will be for the home page and will show top articles and all articles.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {

        // Required empty public constructor
    }

    public static HomeFragment newInstance() {

        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Get core UI elements
        RecyclerView topStoriesRecyclerView = view.findViewById(R.id.topStoriesRecyclerView);
        RecyclerView allStoriesRecyclerView = view.findViewById(R.id.allStoriesRecyclerView);

        // For this to work, we need to get our view model that contains the reference to the data access interface.
        ArticleDataAccessViewModel articleDataAccessViewModel = new ViewModelProvider(requireActivity()).get(ArticleDataAccessViewModel.class);
        IArticleDataAccess articleDataAccess = articleDataAccessViewModel.getArticleDataAccess().getValue();

        // We need to create two separate recycler views. One for top stories and one for all stories.
        // The recycle view implementation will be the same and we can just use two different Array Lists.
        // One array list will contain all articles, and the other will contain the top stories (I'll just do 5 for now based on ranking number)

        // Top stories
        ArticleRecyclerAdapter topStoriesRecycleAdapter = new ArticleRecyclerAdapter(getContext(), articleDataAccess.GetTopRankedArticles(5));

        // All articles
        ArticleRecyclerAdapter allArticlesRecycleAdapter = new ArticleRecyclerAdapter(getContext(), articleDataAccess.GetAllArticles());

        // Attach the adapter to the recycler views
        topStoriesRecyclerView.setAdapter(topStoriesRecycleAdapter);
        topStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        allStoriesRecyclerView.setAdapter(allArticlesRecycleAdapter);
        allStoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Inflate the layout for this fragment
        return view;
    }
}