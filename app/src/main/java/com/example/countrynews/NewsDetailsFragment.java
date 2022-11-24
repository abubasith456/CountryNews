package com.example.countrynews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.countrynews.databinding.FragmentNewsDetailsBinding;
import com.example.countrynews.db.AppDatabase;
import com.example.countrynews.model.offlineNews.News;
import com.example.countrynews.model.newsDetails.DetailsModel;
import com.example.countrynews.viewModel.NewsDetailsViewModel;


public class NewsDetailsFragment extends Fragment {

    private String title, description, author, dateAndTime, urlToImage, url, content;
    private FragmentNewsDetailsBinding fragmentNewsDetailsBinding;
    private NewsDetailsViewModel newsDetailsViewModel;
    private DetailsModel detailsModel;

    public NewsDetailsFragment(String title, String description, String author, String dateAndTime, String urlToImage, String url, String content) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.dateAndTime = dateAndTime;
        this.urlToImage = urlToImage;
        this.url = url;
        this.content = content;

        detailsModel = new DetailsModel(title, description, author, dateAndTime, urlToImage, url, content);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("LifeCycle Details ==>", "onCreate");
        try {
                //Here we want add some codes
        } catch (Exception e) {
            Log.e("Error==>", e.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("LifeCycle Details ==>", "onCreateView");
        newsDetailsViewModel = new ViewModelProvider(requireActivity()).get(NewsDetailsViewModel.class);
        fragmentNewsDetailsBinding = FragmentNewsDetailsBinding.inflate(getLayoutInflater());
        fragmentNewsDetailsBinding.setDetailsModel(detailsModel);
        fragmentNewsDetailsBinding.setImageUrl(urlToImage);
        fragmentNewsDetailsBinding.setFragmentDetailsViewModel(newsDetailsViewModel);
        newsDetailsViewModel.getNewsData(title, description, author, dateAndTime, urlToImage);
        newsDetailsViewModel.getUrl(url);
        return fragmentNewsDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("LifeCycle Details ==>", "onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("LifeCycle Details ==>", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("LifeCycle Details ==>", "OnPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("LifeCycle Details ==>", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("LifeCycle Details ==>", "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("LifeCycle Details ==>", "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("LifeCycle Details ==>", "onDestroy");
    }
}