package com.example.countrynews.test;

import android.app.Activity;
import android.widget.Toast;

import com.example.countrynews.model.news.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    private Activity activity;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void getNewsHeadLines(OnFetchDataListener listener, String category, String query) {
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<NewsResponse> call = callNewsApi.callHeadlines("in", category, query, "a49844b91eb748bb9d3458aa0794db69");
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
                }

                listener.onFetchData(response.body().getArticles(), response.message());
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                listener.onErrorMessage(t.getMessage());
            }
        });
    }


    public RequestManager(Activity activity) {
        this.activity = activity;
    }

    public interface CallNewsApi {
        @GET("top-headlines")
        Call<NewsResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String api_key
        );
    }
}
