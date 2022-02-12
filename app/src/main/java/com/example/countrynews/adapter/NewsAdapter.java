package com.example.countrynews.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrynews.databinding.ListViewRowNewsBinding;
import com.example.countrynews.model.NewsModel;
import com.example.countrynews.model.news.NewsHeadLines;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder> {

    private Activity activity;
    private List<NewsHeadLines> headLines;

    public NewsAdapter(Activity activity, List<NewsHeadLines> headLines) {
        this.activity = activity;
        this.headLines = headLines;
    }

    @NonNull
    @Override
    public NewsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListViewRowNewsBinding listViewRowNewsBinding = ListViewRowNewsBinding.inflate(layoutInflater, parent, false);
        return new NewsAdapterViewHolder(listViewRowNewsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapterViewHolder holder, int position) {
        headLines.get(position);
        holder.listViewRowNewsBinding.setNewsModel((NewsHeadLines) headLines);

    }

//    public void getScannedData(ArrayList<NewsModel> arrayList) {
//        this.headLines = arrayList;
//        notifyDataSetChanged();
//
//    }

    @Override
    public int getItemCount() {
        if (headLines != null) {
            return headLines.size();
        } else {
            return 0;
        }
    }

    public static class NewsAdapterViewHolder extends RecyclerView.ViewHolder {

        ListViewRowNewsBinding listViewRowNewsBinding;

        public NewsAdapterViewHolder(@NonNull ListViewRowNewsBinding listViewRowNewsBinding) {
            super(listViewRowNewsBinding.getRoot());
            this.listViewRowNewsBinding = listViewRowNewsBinding;
        }
    }

}
