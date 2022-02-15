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

import com.example.countrynews.adapter.OfflineNewsAdapter;
import com.example.countrynews.databinding.FragmentOfflineNewsBinding;
import com.example.countrynews.model.News;
import com.example.countrynews.viewModel.OfflineNewsViewModel;

import java.util.List;

public class OfflineNewsFragment extends Fragment {

    private FragmentOfflineNewsBinding fragmentOfflineNewsBinding;
    private OfflineNewsViewModel offlineNewsViewModel;
    private OfflineNewsAdapter offlineNewsAdapter;

    public OfflineNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offlineNewsAdapter = new OfflineNewsAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        offlineNewsViewModel = new ViewModelProvider(getActivity()).get(OfflineNewsViewModel.class);
        fragmentOfflineNewsBinding = FragmentOfflineNewsBinding.inflate(getLayoutInflater());
        fragmentOfflineNewsBinding.setLifecycleOwner(this);
        offlineNewsViewModel.getFragment(OfflineNewsFragment.this);
        fragmentOfflineNewsBinding.setOfflineViewModel(offlineNewsViewModel);
        // Inflate the layout for this fragment
        return fragmentOfflineNewsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            offlineNewsViewModel.getOfflineData().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
                @Override
                public void onChanged(List<News> news) {
                    offlineNewsAdapter.getOfflineData(news);
                    fragmentOfflineNewsBinding.recyclerViewOfflineView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    fragmentOfflineNewsBinding.recyclerViewOfflineView.setHasFixedSize(true);
                    fragmentOfflineNewsBinding.recyclerViewOfflineView.setAdapter(offlineNewsAdapter);
                }
            });
        } catch (Exception e) {
            Log.e("Error==> ", e.getMessage());
        }
    }
}