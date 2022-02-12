package com.example.countrynews.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrynews.R;
import com.example.countrynews.model.news.NewsHeadLines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private Activity activity;
    private List<NewsHeadLines> headLines;

    public CustomAdapter(Activity activity, List<NewsHeadLines> headLines) {
        this.activity = activity;
        this.headLines = headLines;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(activity).inflate(R.layout.list_view_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.text_title.setText(headLines.get(position).getTitle());
        holder.text_description.setText(headLines.get(position).getDescription());
        holder.textAuthor.setText(headLines.get(position).getAuthor());
        holder.textViewTitleDateOrTime.setText(headLines.get(position).getPublishedAt());
        if (headLines.get(position).getUrlToImage() != null) {
            Picasso.get().load(headLines.get(position).getUrlToImage()).into(holder.imageView);
        } else {

        }

    }


    @Override
    public int getItemCount() {
        return headLines.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView text_title, text_description, textAuthor,textViewTitleDateOrTime;
        ImageView imageView;
        ConstraintLayout constraintLayout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            text_title = itemView.findViewById(R.id.textViewTitle);
            text_description = itemView.findViewById(R.id.textViewDescription);
            textAuthor = itemView.findViewById(R.id.textViewAuthor);
            imageView = itemView.findViewById(R.id.imageViewNewsImage);
            constraintLayout = itemView.findViewById(R.id.constarinLayout);
            textViewTitleDateOrTime=itemView.findViewById(R.id.textViewTitleDateOrTime);


        }
    }
}
