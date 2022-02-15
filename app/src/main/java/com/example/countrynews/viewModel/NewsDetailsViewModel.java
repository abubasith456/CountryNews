package com.example.countrynews.viewModel;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.countrynews.db.AppDatabase;
import com.example.countrynews.model.Category;
import com.example.countrynews.model.offlineNews.News;

public class NewsDetailsViewModel extends AndroidViewModel {

    private String url;

//    public MutableLiveData<String> url=new MutableLiveData<>();

    public NewsDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getUrl(String url) {
        this.url = url;
    }

    public void getNewsData(String title, String description, String author, String dateAndTime, String urlToImage) {
        try {
            AppDatabase db = AppDatabase.getDbInstance(this.getApplication());
            News news = new News();
            news.title = title;
            news.description = description;
            news.author = author;
            news.date_and_time = dateAndTime;
            news.image_url = urlToImage;
            db.userDao().insertNewsData(news);
        } catch (Exception e) {
            Log.e("Error==>", e.getMessage());
        }
    }

    public void onUrlClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().getApplicationContext().startActivity(browserIntent);
//        Toast.makeText(getApplication(), ""+url, Toast.LENGTH_SHORT).show();
    }

}
