package com.application.newsapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.application.newsapp.DataAccess.IArticleDataAccess;

/**
 * We will use the view model to share our implementation of IDataAccess.
 * This source was used as a guide to learn how to do this.
 */
public class ArticleDataAccessViewModel extends ViewModel {

    private MutableLiveData<IArticleDataAccess> articleDataAccess = new MutableLiveData<IArticleDataAccess>();

    public void setDataAccess(IArticleDataAccess newArticleDataAccess) {
        articleDataAccess.setValue(newArticleDataAccess);
    }

    public LiveData<IArticleDataAccess> getArticleDataAccess(){
        return articleDataAccess;
    }
}
