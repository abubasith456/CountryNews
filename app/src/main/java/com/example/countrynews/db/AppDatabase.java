package com.example.countrynews.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.countrynews.model.offlineNews.News;

@Database(entities = {News.class}, version  = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NewsDao userDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "OFFLINE_NEWS")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
