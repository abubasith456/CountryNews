package com.example.countrynews.model.newsDetails;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.countrynews.R;

public class DetailsModel {

    String title, description, author, dateAndTime, urlToImage, url, content;

    public DetailsModel(String title, String description, String author, String dateAndTime, String urlToImage, String url, String content) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.dateAndTime = dateAndTime;
        this.urlToImage = urlToImage;
        this.url = url;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @BindingAdapter("detailImage")
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
