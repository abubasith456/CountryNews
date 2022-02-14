package com.example.countrynews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.countrynews.databinding.FragmentNewsBinding;
import com.example.countrynews.databinding.FragmentNewsDetailsBinding;
import com.example.countrynews.model.DetailsModel;
import com.example.countrynews.viewModel.NewsDetailsViewModel;


public class NewsDetailsFragment extends Fragment {

    private String title, description, author, dateAndTime, urlToImage, url;
    private FragmentNewsDetailsBinding fragmentNewsDetailsBinding;
    private NewsDetailsViewModel newsDetailsViewModel;
    private DetailsModel detailsModel;

    public NewsDetailsFragment(String title, String description, String author, String dateAndTime, String urlToImage, String url) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.dateAndTime = dateAndTime;
        this.urlToImage = urlToImage;
        this.url = url;

        detailsModel = new DetailsModel(title, description, author, dateAndTime, urlToImage, url);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        newsDetailsViewModel = new ViewModelProvider(requireActivity()).get(NewsDetailsViewModel.class);
        fragmentNewsDetailsBinding = FragmentNewsDetailsBinding.inflate(getLayoutInflater());
        fragmentNewsDetailsBinding.setDetailsModel(detailsModel);
        fragmentNewsDetailsBinding.setImageUrl(urlToImage);
        fragmentNewsDetailsBinding.setFragmentDetailsViewModel(newsDetailsViewModel);
        newsDetailsViewModel.getUrl(url);
        return fragmentNewsDetailsBinding.getRoot();
    }
}