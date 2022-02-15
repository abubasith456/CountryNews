package com.example.countrynews.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrynews.databinding.ListViewOfflineNewsBinding;
import com.example.countrynews.model.News;

import java.util.List;

public class OfflineNewsAdapter extends RecyclerView.Adapter<OfflineNewsAdapter.OfflineNewsViewHolder> {
    private FragmentActivity activity;
    List<News> offlineNews;

    @NonNull
    @Override
    public OfflineNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListViewOfflineNewsBinding listViewOfflineNewsBinding = ListViewOfflineNewsBinding.inflate(layoutInflater, parent, false);
        return new OfflineNewsViewHolder(listViewOfflineNewsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OfflineNewsViewHolder holder, int position) {
        final News news = offlineNews.get(position);
        holder.listViewOfflineNewsBinding.setNews(news);
        holder.listViewOfflineNewsBinding.setImageUrl(news.image_url);
    }

    public void getOfflineData(List<News> offlineNews) {
        this.offlineNews = offlineNews;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (offlineNews.size() != 0) {
            return offlineNews.size();
        } else {
            return 0;
        }
    }

    public class OfflineNewsViewHolder extends RecyclerView.ViewHolder {

        ListViewOfflineNewsBinding listViewOfflineNewsBinding;

        public OfflineNewsViewHolder(@NonNull ListViewOfflineNewsBinding listViewOfflineNewsBinding) {
            super(listViewOfflineNewsBinding.getRoot());
            this.listViewOfflineNewsBinding = listViewOfflineNewsBinding;
        }
    }

}
