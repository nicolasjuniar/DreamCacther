package com.cheteam.dreamcatcher.ArticlePreview.Controller;

import com.cheteam.dreamcatcher.ArticlePreview.API.ViewArticleApi;
import com.cheteam.dreamcatcher.ArticlePreview.Model.BookmarkRequest;
import com.cheteam.dreamcatcher.ArticlePreview.Model.BookmarkResponse;
import com.cheteam.dreamcatcher.ArticlePreview.Model.ViewArticleResponse;
import com.cheteam.dreamcatcher.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 13/09/2017.
 */

public class ArticleController {
    ViewArticleApi service;
    Call<ViewArticleResponse> CallViewArticle;
    Call<BookmarkResponse> CallBookmark;
    onViewArticleResponse listener;
    onAddBookmarkResponse listener2;

    public ArticleController(onViewArticleResponse listener) {
        this.listener = listener;
    }
    public ArticleController(onAddBookmarkResponse listener) {
        this.listener2 = listener;
    }

    public void GetArticle(int id_post)
    {
        service= ServiceGenerator.createService(ViewArticleApi.class);
        CallViewArticle=service.getArticle(id_post);
        CallViewArticle.enqueue(new Callback<ViewArticleResponse>() {
            @Override
            public void onResponse(Call<ViewArticleResponse> call, Response<ViewArticleResponse> response) {
                listener.getViewArticleResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<ViewArticleResponse> call, Throwable t) {
                listener.getViewArticleResponse(true,null,t);
            }
        });
    }

    public void addBookmark(BookmarkRequest body, String token)
    {
        service=ServiceGenerator.createService(ViewArticleApi.class);
        CallBookmark=service.Bookmark(body,token);
        CallBookmark.enqueue(new Callback<BookmarkResponse>() {
            @Override
            public void onResponse(Call<BookmarkResponse> call, Response<BookmarkResponse> response) {
                listener2.getAddBookmarkResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<BookmarkResponse> call, Throwable t) {
                listener2.getAddBookmarkResponse(true,null,t);
            }
        });
    }

    public interface onViewArticleResponse {
        void getViewArticleResponse(boolean error, ViewArticleResponse response,Throwable t);
    }

    public interface onAddBookmarkResponse {
        void getAddBookmarkResponse(boolean error, BookmarkResponse response,Throwable t);
    }
}
