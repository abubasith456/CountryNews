package com.example.countrynews.viewModel;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class NewsDetailsViewModel extends AndroidViewModel {

    private String url;

//    public MutableLiveData<String> url=new MutableLiveData<>();

    public NewsDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getUrl(String url) {
        this.url = url;
    }


    public void onUrlClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().getApplicationContext().startActivity(browserIntent);
//        Toast.makeText(getApplication(), ""+url, Toast.LENGTH_SHORT).show();
    }


}
