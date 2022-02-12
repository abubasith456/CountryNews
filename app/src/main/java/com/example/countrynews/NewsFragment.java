package com.example.countrynews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.countrynews.adapter.CustomAdapter;
import com.example.countrynews.adapter.NewsAdapter;
import com.example.countrynews.databinding.FragmentNewsBinding;
import com.example.countrynews.model.NewsModel;
import com.example.countrynews.model.TestModel;
import com.example.countrynews.model.news.NewsHeadLines;
import com.example.countrynews.model.news.NewsResponse;
import com.example.countrynews.rest_client.BCRequests;
import com.example.countrynews.test.OnFetchDataListener;
import com.example.countrynews.test.RequestManager;
import com.example.countrynews.viewModel.NewsFragmentViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding fragmentNewsBinding;
    private NewsFragmentViewModel newsFragmentViewModel;
    private static List<NewsHeadLines> list;
    String api = "a49844b91eb748bb9d3458aa0794db69";
    //    public FragmentNewsBinding fragmentNewsBinding;
    private String country = "in";
    //    private String q = "bitcoin";
    ArrayList<ArrayList<NewsResponse.Article>> arrayList;
    NewsAdapter newsAdapter;
    ArrayList<NewsResponse.Article> newsResponse;
    ArrayList<NewsModel> modelArrayList;
    CustomAdapter customAdapter;
    public List<NewsHeadLines> newsHeadLinesList;
    private com.example.countrynews.model.NewsResponse newsHeadLines;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsHeadLinesList = new ArrayList<>();
//        RequestManager manager = new RequestManager(getActivity());
//        manager.getNewsHeadLines(listener, "general", null);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        newsFragmentViewModel = new ViewModelProvider(requireActivity()).get(NewsFragmentViewModel.class);
        fragmentNewsBinding = FragmentNewsBinding.inflate(getLayoutInflater());
        fragmentNewsBinding.setNewsHeadlines(newsHeadLines);
        fragmentNewsBinding.setLifecycleOwner(this);
        return fragmentNewsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            newsFragmentViewModel.getNewsHeadlines().observe(getViewLifecycleOwner(), new Observer<List<NewsHeadLines>>() {
                @Override
                public void onChanged(List<NewsHeadLines> list) {
                    Log.e("=====> ", String.valueOf(list));
                    int size=list.size();
                    for (int i = 0; i <= size - 1; i++) {
                        newsHeadLinesList.add(list.get(i));
                    }
                    fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
                    fragmentNewsBinding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
                    customAdapter = new CustomAdapter(getActivity(), newsHeadLinesList);
                    fragmentNewsBinding.recyclerViewNews.setAdapter(customAdapter);
                }
            });
//            Call<com.example.countrynews.model.NewsResponse> call = BCRequests.getInstance().getBCRestService().callHeadlines(country, "business", null, api);
//            call.enqueue(new Callback<com.example.countrynews.model.NewsResponse>() {
//                @Override
//                public void onResponse(Call<com.example.countrynews.model.NewsResponse> call, Response<com.example.countrynews.model.NewsResponse> response) {
//                    if (response.isSuccessful()) {
//                        Log.e("Total result ==>", String.valueOf(response.body().getArticles().get(0).getDescription()));
////                        Log.e("==>", String.valueOf(response.body().getArticles().indexOf(modelArrayList)));
//                        newsHeadLinesList = new ArrayList<>();
//                        int size = response.body().getArticles().size();
//                        response.body().getArticles().indexOf(call);
//                        for (int i = 0; i <= size - 1; i++) {
//                            newsHeadLinesList.add(response.body().getArticles().get(i));
//                            fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
//                            fragmentNewsBinding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
//                            customAdapter = new CustomAdapter(getActivity(), newsHeadLinesList);
//                            fragmentNewsBinding.recyclerViewNews.setAdapter(customAdapter);
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<com.example.countrynews.model.NewsResponse> call, Throwable t) {
//
//                }
//            });

//            arrayList = new ArrayList<ArrayList<NewsResponse.Article>>();
//            fragmentNewsBinding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
//            fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
////            newsAdapter = new NewsAdapter();
////            newsAdapter.getScannedData((ArrayList<NewsModel>) );
//            Call<TestModel> testModelCall=BCRequests.getInstance().getBCRestService().getNews(country, api);
//              testModelCall.enqueue(new Callback<TestModel>() {
//                        @Override
//                        public void onResponse(Call<TestModel> call, Response<TestModel> response) {
//                            if (response.isSuccessful()){
//                                modelArrayList = new ArrayList<>();
//                                Log.e("Result==>",String.valueOf(response.body().getTotalResult()));
////                                modelArrayList.addAll(response.body().getArrayList());
////                                newsAdapter.notifyDataSetChanged();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<TestModel> call, Throwable t) {
//                            Log.e("Result==>",t.getMessage());
//
//                        }
//                    });


//            fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
//            fragmentNewsBinding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
//            customAdapter = new CustomAdapter(getActivity(), list);
//            fragmentNewsBinding.recyclerViewNews.setAdapter(customAdapter);


        } catch (Exception e) {
            Log.e("Error ==>", e.getMessage());
        }

    }

//    public static OnFetchDataListener<com.example.countrynews.model.NewsResponse> listener = new OnFetchDataListener<com.example.countrynews.model.NewsResponse>() {
//        @Override
//        public void onFetchData(List<NewsHeadLines> list, String message) {
////          shoeNews(list);
//            NewsFragment.list = list;
//
//        }
//
//        @Override
//        public void onErrorMessage(String message) {
//
//        }
//    };

//    private static void shoeNews(List<NewsHeadLines> list) {
//        fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
////        fragmentNewsBinding.recyclerViewNews.setLayoutManager(new GridLayoutManager());
//    }
}