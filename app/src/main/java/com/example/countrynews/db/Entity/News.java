package com.example.countrynews.db.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

}
