package com.cheteam.dreamcatcher.ArticlePreview.Controller;

import com.cheteam.dreamcatcher.AddPost.View.API.AddPostApi;
import com.cheteam.dreamcatcher.AddPost.View.Model.AddPostRequest;
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
    ViewArticleApi service2;
    Call<ViewArticleResponse> CallViewArticle;

    Call<BookmarkResponse> CallBookmark;
    onViewArticleResponse listener;
    onAddBookmarkResponse listener2;

    AddPostApi service;
    Call<AddPostRequest> CallAddPost;
    onAddPostRequest listener3;

    public interface onAddPostRequest{
        void onAddPostRequest(boolean error, AddPostRequest response, Throwable t);
    }

    public ArticleController(onAddPostRequest listener3){
        this.listener3 = listener3;
    }
    public ArticleController(onAddBookmarkResponse listener3) {
        this.listener2 = listener3;
    }


    public ArticleController(onViewArticleResponse listener3) {
        this.listener = listener;
    }

    public void GetArticle(int id_post)
    {
        service2 = ServiceGenerator.createService(ViewArticleApi.class);
        CallViewArticle= service2.getArticle(id_post);
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
        service2=ServiceGenerator.createService(ViewArticleApi.class);
        CallBookmark=service2.Bookmark(body,token);
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



    public void addPost(AddPostRequest post){
        service = ServiceGenerator.createService(AddPostApi.class);
        CallAddPost = service.postArticle(post);
        CallAddPost.enqueue(new Callback<AddPostRequest>() {
            @Override
            public void onResponse(Call<AddPostRequest> call, Response<AddPostRequest> response) {
                listener3.onAddPostRequest(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<AddPostRequest> call, Throwable throwable) {
                listener3.onAddPostRequest(true,null,throwable);
            }
        });
    }

    public interface onAddBookmarkResponse {
        void getAddBookmarkResponse(boolean error, BookmarkResponse response,Throwable t);
    }
}
