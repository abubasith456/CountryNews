package com.example.countrynews.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.countrynews.MainActivity;
import com.example.countrynews.messages.LoginMessages;
import com.example.countrynews.repositories.AuthenticationRepository;

import org.greenrobot.eventbus.EventBus;

public class MainActivityViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> onAppBarVisible = new MutableLiveData<>();
    private AuthenticationRepository repository;
    private MainActivity mainActivity;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        onAppBarVisible.setValue(true);
        repository = new AuthenticationRepository(application);
    }

    public void getActivity(MainActivity mainActivity){
        this.mainActivity=mainActivity;
        boolean test = false;
        EventBus.getDefault().post(new LoginMessages(false));
        if (!test) {
            Log.e("test==> ", "" + test);
        } else {
            Log.e("test==> ", "Null");
        }
    }

    public void checkExistUser() {
        repository.checkExistUser(mainActivity);
    }

}
