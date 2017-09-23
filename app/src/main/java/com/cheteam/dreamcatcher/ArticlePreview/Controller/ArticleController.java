package com.cheteam.dreamcatcher.ArticlePreview.Controller;

import com.cheteam.dreamcatcher.AddPost.View.API.AddPostApi;
import com.cheteam.dreamcatcher.AddPost.View.Model.AddPostRequest;
import com.cheteam.dreamcatcher.ArticlePreview.API.ViewArticleApi;
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
    onViewArticleResponse listener2;

    AddPostApi service;
    Call<AddPostRequest> CallAddPost;
    onAddPostRequest listener;

    public interface onAddPostRequest{
        void onAddPostRequest(boolean error, AddPostRequest response, Throwable t);
    }

    public ArticleController(onAddPostRequest listener){
        this.listener = listener;
    }


    public ArticleController(onViewArticleResponse listener) {
        this.listener2 = listener;
    }

    public void GetArticle(int id_post)
    {
        service2 = ServiceGenerator.createService(ViewArticleApi.class);
        CallViewArticle= service2.getArticle(id_post);
        CallViewArticle.enqueue(new Callback<ViewArticleResponse>() {
            @Override
            public void onResponse(Call<ViewArticleResponse> call, Response<ViewArticleResponse> response) {
                listener2.getViewArticleResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<ViewArticleResponse> call, Throwable t) {
                listener2.getViewArticleResponse(true,null,t);
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
                listener.onAddPostRequest(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<AddPostRequest> call, Throwable throwable) {
                listener.onAddPostRequest(true,null,throwable);
            }
        });
    }

}
