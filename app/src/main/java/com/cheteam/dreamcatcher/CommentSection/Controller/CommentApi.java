package com.cheteam.dreamcatcher.CommentSection.Controller;

import com.cheteam.dreamcatcher.ArticlePreview.Model.ArticleModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Rahmat Al Hakam on 10/09/2017.
 */

public interface CommentApi {
    @GET("/v1/posts/{id_post}/comments/{id_comment}")
    Call<ArticleModel> getArticle(@Path("id_comment") int id_comment,
                                  @Path("id_post") int id_post);
}
