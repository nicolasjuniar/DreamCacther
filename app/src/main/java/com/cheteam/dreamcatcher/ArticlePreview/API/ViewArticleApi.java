package com.cheteam.dreamcatcher.ArticlePreview.API;

import com.cheteam.dreamcatcher.AddPost.View.Model.AddPostRequest;
import com.cheteam.dreamcatcher.ArticlePreview.Model.ViewArticleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Rahmat Al Hakam on 09/09/2017.
 */

public interface ViewArticleApi {
    @GET("v1/posts/{id_post}")
    Call<ViewArticleResponse> getArticle(@Path("id_post") int id_post);

    @POST("/v1/posts")
    Call<AddPostRequest> postArticle(@Body AddPostRequest post);
}
