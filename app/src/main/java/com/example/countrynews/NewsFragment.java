package com.example.countrynews;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.countrynews.adapter.NewsAdapter;
import com.example.countrynews.broadcast.MyBroadcastReceiver;
import com.example.countrynews.databinding.FragmentNewsBinding;
import com.example.countrynews.model.news.NewsHeadLines;
import com.example.countrynews.model.news.NewsResponse;
import com.example.countrynews.utils.Utils;
import com.example.countrynews.viewModel.NewsFragmentViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding fragmentNewsBinding;
    private NewsFragmentViewModel newsFragmentViewModel;
    private NewsAdapter newsAdapter;
    public List<NewsHeadLines> newsHeadLinesList;
    private Handler handler = new Handler();
    private int apiDelayed = 5 * 1000; //1 second=1000 milisecond, 5*1000=5seconds
    private Runnable runnable;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("LifeCycle news ==>", "onCreate");
        newsHeadLinesList = new ArrayList<>();
        newsAdapter = new NewsAdapter();
//        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                Toast.makeText(getActivity(), "Back Pressed", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
//        checkInternetConnection();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("LifeCycle news ==>", "onCreateView");
        newsFragmentViewModel = new ViewModelProvider(requireActivity()).get(NewsFragmentViewModel.class);
        fragmentNewsBinding = FragmentNewsBinding.inflate(getLayoutInflater());
        fragmentNewsBinding.setLifecycleOwner(this);
        newsFragmentViewModel.getFragment(NewsFragment.this);
        fragmentNewsBinding.setNewsViewModel(newsFragmentViewModel);
        newsFragmentViewModel.getBindView(fragmentNewsBinding);
        return fragmentNewsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("LifeCycle news ==>", "onViewCreated");
        try {
            loadNewsData();
            loadCategoryNewsData();
        } catch (Exception e) {
            Log.e("Error ==>", e.getMessage());
        }

    }

    public void loadNewsData() {
        try {
            newsHeadLinesList.clear();
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
                    newsAdapter.getNewsData(newsHeadLinesList, getActivity());
                }
            });
        } catch (Exception e) {
            Log.e("Error ==>", e.getMessage());
        }
    }

    private void loadCategoryNewsData() {
        try {
            newsFragmentViewModel.getCategoryNews().observe(getViewLifecycleOwner(), new Observer<List<NewsHeadLines>>() {
                @Override
                public void onChanged(List<NewsHeadLines> list) {
                    Log.e("=====> ", String.valueOf(list));
                    newsHeadLinesList.clear();
                    int size = list.size();
                    for (int i = 0; i <= size - 1; i++) {
                        newsHeadLinesList.add(list.get(i));
                    }
                    newsAdapter.getNewsData(newsHeadLinesList, getActivity());
                    fragmentNewsBinding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
                    fragmentNewsBinding.recyclerViewNews.setHasFixedSize(true);
                    fragmentNewsBinding.recyclerViewNews.setAdapter(newsAdapter);
                }
            });
        } catch (Exception e) {
            Log.e("Error ==>", e.getMessage());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("LifeCycle News ==>", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("LifeCycle News ==>", "OnPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("LifeCycle News ==>", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("LifeCycle News ==>", "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("LifeCycle News ==>", "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("LifeCycle News ==>", "onDestroy");
    }

}