package com.example.countrynews;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.countrynews.adapter.CustomAdapter;
import com.example.countrynews.adapter.NewsAdapter;
import com.example.countrynews.databinding.FragmentNewsBinding;
import com.example.countrynews.model.NewsModel;
import com.example.countrynews.model.news.NewsHeadLines;
import com.example.countrynews.model.news.NewsResponse;
import com.example.countrynews.test.RequestManager;
import com.example.countrynews.viewModel.NewsFragmentViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding fragmentNewsBinding;
    private NewsFragmentViewModel newsFragmentViewModel;
    //    private static List<NewsHeadLines> list;
//    private String api = "a49844b91eb748bb9d3458aa0794db69";
//    public FragmentNewsBinding fragmentNewsBinding;
//    private String country = "in";
//    private String q = "bitcoin";
////  ArrayList<ArrayList<NewsResponse.Article>> arrayList;
//    ArrayList<NewsResponse.Article> newsResponse;
//    ArrayList<NewsModel> modelArrayList;
//    CustomAdapter customAdapter;
    private NewsResponse newsHeadLines;
    private NewsAdapter newsAdapter;
    public List<NewsHeadLines> newsHeadLinesList;
    private FirebaseAuth auth;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsHeadLinesList = new ArrayList<>();
        newsAdapter = new NewsAdapter();
        auth = FirebaseAuth.getInstance();
//        RequestManager manager = new RequestManager(getActivity());
//        manager.getNewsHeadLines(listener, "general", null);

//        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                Toast.makeText(getActivity(), "Back Pressed", Toast.LENGTH_SHORT).show();
////                fragmentLoginBinding.layoutLoginPage.setVisibility(View.GONE);
//                auth.signOut();
//                Fragment loginFragment = new LoginFragment();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frameLayoutContainer, loginFragment);
////                transaction.addToBackStack(null);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        newsFragmentViewModel = new ViewModelProvider(requireActivity()).get(NewsFragmentViewModel.class);
        fragmentNewsBinding = FragmentNewsBinding.inflate(getLayoutInflater());
        fragmentNewsBinding.setNewsHeadlines(newsHeadLines);
        fragmentNewsBinding.setLifecycleOwner(this);
        newsFragmentViewModel.getFragment(NewsFragment.this);
        fragmentNewsBinding.setNewsViewModel(newsFragmentViewModel);
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
                    int size = list.size();
                    for (int i = 0; i <= size - 1; i++) {
                        newsHeadLinesList.add(list.get(i));
                    }
                    fragmentNewsBinding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
                    fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
                    fragmentNewsBinding.recyclerViewNews.setAdapter(newsAdapter);
                    newsAdapter.getScannedData(newsHeadLinesList, getActivity());

                }
            });
            loadCategory();
        } catch (Exception e) {
            Log.e("Error ==>", e.getMessage());
        }

    }

    private void loadCategory() {
        try {
            newsFragmentViewModel.getCategoryNews().observe(getViewLifecycleOwner(), new Observer<List<NewsHeadLines>>() {
                @Override
                public void onChanged(List<NewsHeadLines> list) {
                    Log.e("=====> ", String.valueOf(list.get(0).getTitle()));
                    int size = list.size();
                    newsHeadLinesList.clear();
                    for (int i = 0; i <= size - 1; i++) {
                        newsHeadLinesList.add(list.get(i));
                    }
                    newsAdapter.getScannedData(newsHeadLinesList, getActivity());
                    fragmentNewsBinding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
                    fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
                    fragmentNewsBinding.recyclerViewNews.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            Log.e("Error ==>", e.getMessage());
        }


    }

//    public static OnFetchDataListener<com.example.countrynews.model.news.NewsResponse> listener = new OnFetchDataListener<com.example.countrynews.model.news.NewsResponse>() {
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


    @Override
    public void onResume() {
        super.onResume();
//        newsAdapter.getScannedData(newsHeadLinesList, getActivity());
    }
}