package com.example.countrynews.rest_client;

import com.example.countrynews.model.NewsResponse;
import com.example.countrynews.model.TestModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface BCRestService {

    @GET("top-headlines")
    Call<NewsResponse> callHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("q") String query,
            @Query("apiKey") String api_key
    );

//    @GET("GET https://newsapi.org/v2/top-headlines?country=in&apiKey=a49844b91eb748bb9d3458aa0794db69")
//    Call<TestModel> getAllNews(
//
//    );

}
