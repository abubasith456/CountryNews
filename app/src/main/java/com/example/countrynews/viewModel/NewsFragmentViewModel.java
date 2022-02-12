package com.example.countrynews.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.countrynews.model.NewsResponse;
import com.example.countrynews.model.news.NewsHeadLines;
import com.example.countrynews.rest_client.BCRequests;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragmentViewModel extends AndroidViewModel {
    String api = "a49844b91eb748bb9d3458aa0794db69";
    String country="in";
    private MutableLiveData<NewsHeadLines> responseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<NewsHeadLines>> newsHeadlines = new MutableLiveData<>();
    public List<NewsHeadLines> newsHeadLinesList;


    public NewsFragmentViewModel(@NonNull Application application) {
        super(application);
        newsHeadLinesList = new ArrayList<>();
    }

    public MutableLiveData<List<NewsHeadLines>> getNewsHeadlines() {

//        Call<NewsResponse> call = BCRequests.getInstance().getBCRestService().callHeadlines("us", "business", null, api);
//        call.enqueue(new Callback<NewsResponse>() {
//            @Override
//            public void onResponse(Call<com.example.countrynews.model.NewsResponse> call, Response<NewsResponse> response) {
//                if (response.isSuccessful()) {
////                    Log.e("Total result ==>", String.valueOf(response.body().getArticles().get().getDescription()));
////                        Log.e("==>", String.valueOf(response.body().getArticles().indexOf(modelArrayList)));
////                    newsHeadlines = new ArrayList<>();
//                    int size = response.body().getArticles().size();
//                    response.body().getArticles().indexOf(call);
//                    for (int i = 0; i <= size - 1; i++) {
//                        newsHeadLinesList.add(response.body().getArticles().get(i));
//                        responseMutableLiveData.postValue(response.body().getArticles().get(i));
////                        fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
////                        fragmentNewsBinding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
////                        customAdapter = new CustomAdapter(getActivity(), newsHeadLinesList);
////                        fragmentNewsBinding.recyclerViewNews.setAdapter(customAdapter);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<com.example.countrynews.model.NewsResponse> call, Throwable t) {
//                Log.e("Error", t.getMessage());
//            }
//        });

        try {
            Call<com.example.countrynews.model.NewsResponse> call = BCRequests.getInstance().getBCRestService().callHeadlines(country, "general", null, api);
            call.enqueue(new Callback<com.example.countrynews.model.NewsResponse>() {
                @Override
                public void onResponse(Call<com.example.countrynews.model.NewsResponse> call, Response<com.example.countrynews.model.NewsResponse> response) {
                    if (response.isSuccessful()) {
                        Log.e("Total result ==>", String.valueOf(response.body().getArticles().get(0).getDescription()));
//                        Log.e("==>", String.valueOf(response.body().getArticles().indexOf(modelArrayList)));
                        newsHeadLinesList = new ArrayList<>();
                        int size = response.body().getArticles().size();
                        response.body().getArticles().indexOf(call);
                        for (int i = 0; i <= size - 1; i++) {
//                            newsHeadLinesList.add(response.body().getArticles().get(i));
                            newsHeadlines.postValue(response.body().getArticles());
//                            fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
//                            fragmentNewsBinding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
//                            customAdapter = new CustomAdapter(getActivity(), newsHeadLinesList);
//                            fragmentNewsBinding.recyclerViewNews.setAdapter(customAdapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<com.example.countrynews.model.NewsResponse> call, Throwable t) {

                }
            });
        } catch (Exception exception) {
            Log.e("Erroe Call ==>", exception.getMessage());
        }

        return newsHeadlines;
    }
}
