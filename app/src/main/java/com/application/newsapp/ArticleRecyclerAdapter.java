package com.application.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleView> {

    private Context context;
    private ArrayList<Article> articles;

    public ArticleRecyclerAdapter(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleRecyclerAdapter.ArticleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.article_card_image_only, parent, false);

        return new ArticleRecyclerAdapter.ArticleView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleRecyclerAdapter.ArticleView holder, int position) {

        // This is where we will update the view with the required information.
        // To my understanding, as views come into the screen, we can use the position to index
        // to get the required data for our articles array.
        holder.getImageView().setImageResource(articles.get(position).getCoverImage());

    }

    @Override
    public int getItemCount() {

        // This is how the recycle view knows how many views to create.
        // With some experimentation, if you return 1, only one article card will appear.
        // Like wise with 2, only two article cards will appear.
        // Hence, we need to return the size of the array list we passed in.
        return articles.size();
    }

    /**
     * This is essentially going to be our view for the article card
     */
    public static class ArticleView extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ArticleView(@NonNull View itemView) {

            super(itemView);

            // Get the UI element references
            imageView = itemView.findViewById(R.id.articleImageView);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
