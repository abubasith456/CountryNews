package com.example.countrynews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.countrynews.adapter.SportsNewsAdapter;
import com.example.countrynews.databinding.FragmentSportsBinding;
import com.example.countrynews.model.news.NewsHeadLines;
import com.example.countrynews.viewModel.SportsFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class SportsFragment extends Fragment {

    private FragmentSportsBinding fragmentSportsBinding;
    private SportsFragmentViewModel sportsFragmentViewModel;
    public List<NewsHeadLines> newsHeadLinesList;
    private SportsNewsAdapter sportsNewsAdapter;

    public SportsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsHeadLinesList = new ArrayList<>();
        sportsNewsAdapter=new SportsNewsAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sportsFragmentViewModel = new ViewModelProvider(requireActivity()).get(SportsFragmentViewModel.class);
        fragmentSportsBinding = FragmentSportsBinding.inflate(getLayoutInflater());
        fragmentSportsBinding.setLifecycleOwner(this);
        fragmentSportsBinding.recyclerViewSportsNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentSportsBinding.recyclerViewSportsNews.setHasFixedSize(true);
        fragmentSportsBinding.recyclerViewSportsNews.setAdapter(sportsNewsAdapter);
        // Inflate the layout for this fragment
        return fragmentSportsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            sportsFragmentViewModel.getSportsNews().observe(getViewLifecycleOwner(), new Observer<List<NewsHeadLines>>() {
                @Override
                public void onChanged(List<NewsHeadLines> list) {
                    int size=list.size();
                    for (int i=0;i<=size-1;i++){
                        newsHeadLinesList.add(list.get(i));
                    }
                    sportsNewsAdapter.getScannedData(newsHeadLinesList);
                }
            });
        } catch (Exception e) {
            Log.e("Error ==>", e.getMessage());
        }
    }
}