package com.example.countrynews.model.offlineNews;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.example.countrynews.R;

@Entity
public class News {

    @PrimaryKey(autoGenerate = true)
    public int news_id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "date_time")
    public String date_and_time;

    @ColumnInfo(name = "image_url")
    public String image_url;

    @BindingAdapter("offlineImage")
    public static void loadImage(ImageView view, String imageUrl) {
        if (imageUrl != null) {
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .into(view);
        } else {
            Glide.with(view.getContext())
                    .load(view.getResources().getDrawable(R.drawable.image_not_available))
                    .into(view);
        }
    }

}
