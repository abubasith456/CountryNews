package com.example.countrynews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrynews.databinding.ListViewRowNewsBinding;
import com.example.countrynews.model.news.NewsHeadLines;

import java.util.List;

public class SportsNewsAdapter extends RecyclerView.Adapter<SportsNewsAdapter.SportsViewHolder> {

    private List<NewsHeadLines> headLines;

    @NonNull
    @Override
    public SportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListViewRowNewsBinding listViewRowNewsBinding = ListViewRowNewsBinding.inflate(layoutInflater, parent, false);
        return new SportsViewHolder(listViewRowNewsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder holder, int position) {
        holder.listViewRowNewsBinding.setNewsModel(headLines.get(position));
        holder.listViewRowNewsBinding.setImageUrl(headLines.get(position).getUrlToImage());
    }

    public void getScannedData(List<NewsHeadLines> headLines) {
        this.headLines = headLines;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (headLines != null) {
            return headLines.size();
        } else {
            return 0;
        }
    }

    public class SportsViewHolder extends RecyclerView.ViewHolder {

        ListViewRowNewsBinding listViewRowNewsBinding;

        public SportsViewHolder(@NonNull ListViewRowNewsBinding listViewRowNewsBinding) {
            super(listViewRowNewsBinding.getRoot());
            this.listViewRowNewsBinding = listViewRowNewsBinding;
        }
    }


}
