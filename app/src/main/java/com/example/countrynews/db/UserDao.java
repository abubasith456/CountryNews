package com.example.countrynews.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.countrynews.db.Entity.News;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM news")
    List<News> getAllUsers();

    @Insert
    void insertUser(News... news);

    @Delete
    void delete(News news);
}
