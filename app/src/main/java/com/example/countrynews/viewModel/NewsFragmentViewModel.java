package com.example.countrynews.viewModel;

import android.app.Application;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.countrynews.NewsFragment;
import com.example.countrynews.OfflineNewsFragment;
import com.example.countrynews.R;
import com.example.countrynews.model.Category;
import com.example.countrynews.model.news.NewsResponse;
import com.example.countrynews.model.news.NewsHeadLines;
import com.example.countrynews.rest_client.BCRequests;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragmentViewModel extends AndroidViewModel {
    private String api = "a49844b91eb748bb9d3458aa0794db69";
    private String country = "in";
    private String category = "general";
    private Application application;
    private MutableLiveData<NewsHeadLines> responseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<NewsHeadLines>> newsHeadlines = new MutableLiveData<>();
    private MutableLiveData<List<NewsHeadLines>> categoryNews = new MutableLiveData<>();
    public List<NewsHeadLines> newsHeadLinesList;
    private NewsFragment newsFragment;


    public NewsFragmentViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        newsHeadLinesList = new ArrayList<>();
    }

    public void getFragment(NewsFragment newsFragment) {
        this.newsFragment = newsFragment;
    }

    public void getAdapterPosition(List<NewsHeadLines> newsHeadLines) {
        Toast.makeText(application.getApplicationContext(), "" + newsHeadLines.get(0), Toast.LENGTH_SHORT).show();
    }

    public MutableLiveData<List<NewsHeadLines>> getNewsHeadlines() {

//        Call<NewsResponse> call = BCRequests.getInstance().getBCRestService().callHeadlines("us", "business", null, api);
//        call.enqueue(new Callback<NewsResponse>() {
//            @Override
//            public void onResponse(Call<com.example.countrynews.model.news.NewsResponse> call, Response<NewsResponse> response) {
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
//            public void onFailure(Call<com.example.countrynews.model.news.NewsResponse> call, Throwable t) {
//                Log.e("Error", t.getMessage());
//            }
//        });

        try {
            Call<NewsResponse> call = BCRequests.getInstance().getBCRestService().callHeadlines(country, category, null, api);
            call.enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
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
                public void onFailure(Call<NewsResponse> call, Throwable t) {

                }
            });
        } catch (Exception exception) {
            Log.e("Erroe Call ==>", exception.getMessage());
        }
        return newsHeadlines;
    }

    public MutableLiveData<List<NewsHeadLines>> getCategoryNews() {
        return categoryNews;
    }

    public void onCategoryClick(View view) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(newsFragment.getActivity());
            builder.setTitle("Select Category")
                    .setItems(Category.selectCategory, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String selectedCategory = Category.selectCategory[which];
                            loadFilterItems(selectedCategory);
//                            textViewCategoryName.setText(selectedCategory);
//                        if (selectedCategory.equals("All")) {
//                            loadAllItems();
//                        } else {
//
//                        }
                        }
                    }).show();
        } catch (Exception exception) {
            Log.e("Error ==> ", "" + exception);
        }
    }

    private void loadFilterItems(String selectedCategory) {
        try {
            Call<NewsResponse> call = BCRequests.getInstance().getBCRestService().callHeadlines(country, selectedCategory, null, api);
            call.enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    if (response.isSuccessful()) {
                        Log.e("Total result ==>", String.valueOf(response.body().getArticles().get(0).getDescription()));
//                        Log.e("==>", String.valueOf(response.body().getArticles().indexOf(modelArrayList)));
//                        newsHeadLinesList.clear();
//                        newsHeadLinesList = new ArrayList<>();
                        int size = response.body().getArticles().size();
                        response.body().getArticles().indexOf(call);
                        for (int i = 0; i <= size - 1; i++) {
//                            newsHeadLinesList.add(response.body().getArticles().get(i));
                            categoryNews.postValue(response.body().getArticles());
//                            fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
//                            fragmentNewsBinding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
//                            customAdapter = new CustomAdapter(getActivity(), newsHeadLinesList);
//                            fragmentNewsBinding.recyclerViewNews.setAdapter(customAdapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {

                }
            });

        } catch (Exception exception) {
            Log.e("Error ==> ", "" + exception);
        }
    }

    public void onOfflineNewsClick(View view) {
        try {

            Fragment fragment = new OfflineNewsFragment();
            FragmentTransaction fragmentTransaction = newsFragment.getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutContainer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } catch (Exception exception) {
            Log.e("Error ==> ", "" + exception);
        }
    }

}
