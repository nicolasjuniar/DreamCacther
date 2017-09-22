package com.cheteam.dreamcatcher.Timeline.API;

import com.cheteam.dreamcatcher.Timeline.Model.BookmarkResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by MPR on 9/23/2017.
 */

public interface BookmarkAPI {
    @GET("v1/bookmarks")
    Call<BookmarkResponse> GetBookmarks();
}
