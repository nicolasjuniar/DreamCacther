package com.cheteam.dreamcatcher.Helper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Nicolas Juniar on 22/09/2017.
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", "auth-value"); // <-- this is the important line

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
