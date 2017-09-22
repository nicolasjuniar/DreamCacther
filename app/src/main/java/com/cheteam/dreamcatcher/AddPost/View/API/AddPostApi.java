package com.cheteam.dreamcatcher.AddPost.View.API;

import com.cheteam.dreamcatcher.AddPost.View.Model.AddPostResponse;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Rahmat Al Hakam on 22/09/2017.
 */

public interface AddPostApi {
    @POST("/v1/posts")
    Call<AddPostResponse> postArticle();
}
