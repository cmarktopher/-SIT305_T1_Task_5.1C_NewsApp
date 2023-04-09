package com.application.newsapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleFragment extends Fragment {

    private static final String ARG_PARAM1 = "ArticleData";
    private static final String ARG_PARAM2 = "ArticleHandler";
    private Article article;

    public ArticleFragment() {
        // Required empty public constructor
    }

    public static ArticleFragment newInstance(Article article, IArticleHandler articleHandler) {
        ArticleFragment fragment = new ArticleFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, article);
        args.putParcelable(ARG_PARAM2, articleHandler);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            article = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_article, container, false);

        // Get core elements
        ImageView articleFragmentImage = view.findViewById(R.id.articleFragmentImage);
        TextView articleFragmentTitle = view.findViewById(R.id.articleFragmentTitle);
        TextView articleFragmentContent = view.findViewById(R.id.articleFragmentContent);
        RecyclerView relatedArticlesRecyclerView = view.findViewById(R.id.articleFragmentRelatedRecycler);

        // Set the properties
        articleFragmentImage.setImageResource(article.getCoverImage());
        articleFragmentTitle.setText(article.getTitle());
        articleFragmentContent.setText(article.getContent());

        // Lets create an ArrayList of articles based on the ids of related articles
        ArrayList<Article> allArticles = HomeFragment.getAllArticles();
        ArrayList<Article> relatedArticles = new ArrayList<>();

        // As per the whole using static all articles thing, this whole thing could be handled better.
        for (int i = 0; i < article.getRelatedArticleIds().length; i++) {

            for (int j = 0; j < allArticles.size(); j++) {

                if (allArticles.get(j).getId() == article.getRelatedArticleIds()[i]){

                    relatedArticles.add(allArticles.get(j));
                }
            }
        }

        // Create our recycler view
        ArticleRecyclerAdapter relatedArticlesRecycleAdapter = new ArticleRecyclerAdapter(getContext(), relatedArticles);
        relatedArticlesRecyclerView.setAdapter(relatedArticlesRecycleAdapter);
        relatedArticlesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Inflate the layout for this fragment
        return view;
    }
}