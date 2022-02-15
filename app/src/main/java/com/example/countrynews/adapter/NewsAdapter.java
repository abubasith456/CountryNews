package com.example.countrynews.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrynews.NewsDetailsFragment;
import com.example.countrynews.R;
import com.example.countrynews.databinding.ListViewRowNewsBinding;
import com.example.countrynews.model.news.NewsHeadLines;
import com.example.countrynews.viewModel.NewsFragmentViewModel;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder> {

    private FragmentActivity activity;
    List<NewsHeadLines> headLines;
    private NewsFragmentViewModel newsFragmentViewModel;

    @NonNull
    @Override
    public NewsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListViewRowNewsBinding listViewRowNewsBinding = ListViewRowNewsBinding.inflate(layoutInflater, parent, false);
        return new NewsAdapterViewHolder(listViewRowNewsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapterViewHolder holder, int position) {
        final NewsHeadLines newsHeadLines = headLines.get(position);
        holder.listViewRowNewsBinding.setNewsModel(newsHeadLines);
        holder.listViewRowNewsBinding.setImageUrl(newsHeadLines.getUrlToImage());
        holder.listViewRowNewsBinding.executePendingBindings();
    }

    public void getNewsData(List<NewsHeadLines> headLines, FragmentActivity activity) {
        this.headLines = headLines;
        this.activity = activity;
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

    public class NewsAdapterViewHolder extends RecyclerView.ViewHolder {

        ListViewRowNewsBinding listViewRowNewsBinding;

        public NewsAdapterViewHolder(@NonNull ListViewRowNewsBinding listViewRowNewsBinding) {
            super(listViewRowNewsBinding.getRoot());
            this.listViewRowNewsBinding = listViewRowNewsBinding;

            listViewRowNewsBinding.constarinLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("==>", "" + headLines.get(getAdapterPosition()).getAuthor());
                    String title = headLines.get(getAdapterPosition()).getTitle();
                    String description = headLines.get(getAdapterPosition()).getDescription();
                    String author = headLines.get(getAdapterPosition()).getAuthor();
                    String dateAndTime = headLines.get(getAdapterPosition()).getPublishedAt();
                    String urlToImage = headLines.get(getAdapterPosition()).getUrlToImage();
                    String url = headLines.get(getAdapterPosition()).getUrl();
                    Fragment intentFragment = new NewsDetailsFragment(title, description, author, dateAndTime, urlToImage, url);
                    FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayoutContainer, intentFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }
    }

}
