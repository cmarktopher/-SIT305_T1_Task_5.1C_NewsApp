package com.application.newsapp.RecyclerAdapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsapp.DataModels.Article;
import com.application.newsapp.Fragments.ArticleFragment;
import com.application.newsapp.DataAccess.IArticleDataAccess;
import com.application.newsapp.R;

import java.util.ArrayList;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleView> {

    private final Context context;
    private final ArrayList<Article> articles;
    private IArticleDataAccess articleHandler;

    public ArticleRecyclerAdapter(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articles = articles;
        this.articleHandler = articleHandler;
    }

    @NonNull
    @Override
    public ArticleRecyclerAdapter.ArticleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.article_view_card, parent, false);

        return new ArticleView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleRecyclerAdapter.ArticleView holder, int position) {

        // This is where we will update the view with the required information.
        // To my understanding, as views come into the screen, we can use the position to index
        // to get the required data for our articles array.
        Drawable articleImageAsVector = AppCompatResources.getDrawable(context.getApplicationContext(), articles.get(position).getCoverImage());

        holder.getArticleButton().setBackground(articleImageAsVector);
        holder.getArticleTitle().setText(articles.get(position).getTitle());

        holder.getArticleButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Move to new fragment
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.fade_out)
                        .setReorderingAllowed(true)
                        .replace(R.id.newsAppFragmentContainer, ArticleFragment.newInstance(articles.get(holder.getAdapterPosition())), null)
                        .commit();
            }
        });

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
    public class ArticleView extends RecyclerView.ViewHolder {

        private final Button articleButton;
        private final TextView articleTitle;

        public ArticleView(@NonNull View itemView) {

            super(itemView);

            // Get the UI element references
            articleButton = itemView.findViewById(R.id.articleButton);
            articleTitle = itemView.findViewById(R.id.articleTitle);
        }

        public Button getArticleButton() {
            return articleButton;
        }

        public TextView getArticleTitle() {return articleTitle;}
    }
}
