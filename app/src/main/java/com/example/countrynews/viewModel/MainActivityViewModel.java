package com.example.countrynews.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> onAppBarVisible = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        onAppBarVisible.setValue(true);
    }
}
