package com.cheteam.dreamcatcher;

import com.cheteam.dreamcatcher.Helper.LoggingInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Nicolas Juniar on 28/08/2016.
 */
public class ServiceGenerator {
//    private static String BASE_URL = "http://dreamcatcherz.herokuapp.com/" ;
    private static String BASE_URL = "http://private-2932ba-morpheus3.apiary-mock.com/" ;

    public ServiceGenerator(){}

    public String getBaseUrl()
    {
        return BASE_URL;
    }

    public static <S> S createService(Class<S> serviceClass) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();


        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return builder.create(serviceClass);
    }
}