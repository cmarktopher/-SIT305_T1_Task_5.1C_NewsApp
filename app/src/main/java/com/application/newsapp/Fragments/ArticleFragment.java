package com.application.newsapp.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.newsapp.DataModels.Article;
import com.application.newsapp.RecyclerAdapters.ArticleRecyclerAdapter;
import com.application.newsapp.DataAccess.IArticleDataAccess;
import com.application.newsapp.R;
import com.application.newsapp.ViewModels.ArticleDataAccessViewModel;

import java.util.ArrayList;

/**
 * Fragment for display a specific article
 * Here, we are utilizing both the view model and parcelables for data.
 */
public class ArticleFragment extends Fragment {

    private static final String ARG_PARAM1 = "ArticleData";
    private Article article;

    public ArticleFragment() {
        // Required empty public constructor
    }

    public static ArticleFragment newInstance(Article article) {
        ArticleFragment fragment = new ArticleFragment();

        // Whatever is creating this fragment will need to pass in an article to display.
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, article);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // We get the article that was passed in.
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

        // Since each article is using Ids as a way to reference related articles, we need access to all articles.
        // As such, we need to get a reference to the view model and the data access interface.
        ArticleDataAccessViewModel articleDataAccessViewModel = new ViewModelProvider(requireActivity()).get(ArticleDataAccessViewModel.class);
        IArticleDataAccess articleDataAccess = articleDataAccessViewModel.getArticleDataAccess().getValue();

        // Lets create an ArrayList of articles based on the ids of related articles
        ArrayList<Article> allArticles = articleDataAccess.GetAllArticles();
        ArrayList<Article> relatedArticles = new ArrayList<>();

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