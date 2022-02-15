package com.example.countrynews.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrynews.OfflineNewsFragment;
import com.example.countrynews.databinding.ListViewOfflineNewsBinding;
import com.example.countrynews.db.AppDatabase;
import com.example.countrynews.model.offlineNews.News;

import java.util.List;

public class OfflineNewsAdapter extends RecyclerView.Adapter<OfflineNewsAdapter.OfflineNewsViewHolder> {
    private OfflineNewsFragment activity;
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

    public void getFragment(OfflineNewsFragment offlineNewsFragment) {
        this.activity = offlineNewsFragment;
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

            listViewOfflineNewsBinding.linearLayoutDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(activity.getActivity());
                    alert.setTitle("Delete")
                            .setMessage("Are you sure! do you want to delete this?");
                    alert.setCancelable(true);
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Toast.makeText(activity.getActivity(), "" + offlineNews.get(getAdapterPosition()).author, Toast.LENGTH_SHORT).show();
                            AppDatabase appDatabase = AppDatabase.getDbInstance(activity.getActivity());
                            News news = new News();
                            news.news_id = offlineNews.get(getAdapterPosition()).news_id;
                            appDatabase.userDao().delete(news);
                            offlineNews.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }
            });

        }
    }

}
