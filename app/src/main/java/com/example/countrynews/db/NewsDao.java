package com.example.countrynews.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.countrynews.model.offlineNews.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAllNewsData();

    @Insert
    void insertNewsData(News... news);

    @Delete
    void delete(News news);
}
