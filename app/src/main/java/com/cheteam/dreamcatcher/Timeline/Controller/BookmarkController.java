package com.cheteam.dreamcatcher.Timeline.Controller;

import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.API.BookmarkAPI;
import com.cheteam.dreamcatcher.Timeline.Model.BookmarkResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MPR on 9/23/2017.
 */

public class BookmarkController {
    BookmarkAPI service;
    Call<BookmarkResponse>CallBookmarkResponse;
    onBookmarkResponse listener;
    public BookmarkController (onBookmarkResponse listener){this.listener = listener;}

    public void GetBookmark(){
        service= ServiceGenerator.createService(BookmarkAPI.class);
        CallBookmarkResponse = service.GetBookmarks();
        CallBookmarkResponse.enqueue(new Callback<BookmarkResponse>() {
            @Override
            public void onResponse(Call<BookmarkResponse> call, Response<BookmarkResponse> response) {
                listener.getBookmarkResponse(false,response.body(),null);

            }

            @Override
            public void onFailure(Call<BookmarkResponse> call, Throwable throwable) {
                listener.getBookmarkResponse(true,null,throwable);

            }
        });
    }
    public interface onBookmarkResponse {
        public void getBookmarkResponse(boolean error, BookmarkResponse response,Throwable throwable);
    }
}
