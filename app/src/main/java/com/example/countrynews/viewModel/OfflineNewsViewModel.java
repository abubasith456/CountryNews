package com.example.countrynews.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.countrynews.OfflineNewsFragment;
import com.example.countrynews.adapter.OfflineNewsAdapter;
import com.example.countrynews.db.AppDatabase;
import com.example.countrynews.model.News;

import java.util.List;

public class OfflineNewsViewModel extends AndroidViewModel {
    public MutableLiveData<List<News>> offlineNewsModelMutable = new MutableLiveData<>();
    private OfflineNewsFragment offlineNewsFragment;
    private OfflineNewsAdapter offlineNewsAdapter;

    public OfflineNewsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getFragment(OfflineNewsFragment offlineNewsFragment) {
        this.offlineNewsFragment = offlineNewsFragment;
    }

    public MutableLiveData<List<News>> getOfflineData() {
        try {
            AppDatabase db = AppDatabase.getDbInstance(this.offlineNewsFragment.getActivity());
            List<News> news = db.userDao().getAllNewsData();
//            offlineNewsAdapter = new OfflineNewsAdapter();
//            offlineNewsAdapter.getOfflineData(news);
            offlineNewsModelMutable.setValue(news);
        } catch (Exception e) {
            Log.e("Error==> ", e.getMessage());
        }

        return offlineNewsModelMutable;
    }
}
