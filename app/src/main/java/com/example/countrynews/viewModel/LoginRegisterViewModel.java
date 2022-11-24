package com.example.countrynews.viewModel;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.Application;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.example.countrynews.ForgotPasswordFragment;
import com.example.countrynews.LoginFragment;
import com.example.countrynews.NewsFragment;
import com.example.countrynews.R;
import com.example.countrynews.RegisterFragment;
import com.example.countrynews.databinding.ActivityMainBinding;
import com.example.countrynews.messages.RegisterMessage;
import com.example.countrynews.repositories.AuthenticationRepository;
import com.example.countrynews.utils.Utils;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.EventBus;

public class LoginRegisterViewModel extends AndroidViewModel {

    public MutableLiveData<String> EmailLogin = new MutableLiveData<>();
    public MutableLiveData<String> PasswordLogin = new MutableLiveData<>();
    public MutableLiveData<String> EmailError = new MutableLiveData<>();
    public MutableLiveData<String> PasswordError = new MutableLiveData<>();
    public MutableLiveData<String> EmailRegister = new MutableLiveData<>();
    public MutableLiveData<String> PasswordRegister = new MutableLiveData<>();
    public MutableLiveData<String> NameRegister = new MutableLiveData<>();
    public MutableLiveData<String> EmailRegisterError = new MutableLiveData<>();
    public MutableLiveData<String> PasswordRegisterError = new MutableLiveData<>();
    public MutableLiveData<String> NameRegisterError = new MutableLiveData<>();
    public MutableLiveData<String> ForgotPassword = new MutableLiveData<>();
    public MutableLiveData<String> ForgotError = new MutableLiveData<>();
    public MutableLiveData<Boolean> onClickResult = new MutableLiveData<>();
    public MutableLiveData<Boolean> onClickRegister = new MutableLiveData<>();
    public MutableLiveData<Boolean> onClickForgotResult = new MutableLiveData<>();
    public ActivityMainBinding activityMainBinding;
    private AuthenticationRepository repository;
    private MutableLiveData<FirebaseUser> userLoginData;
    private Application application;
    //    private ActivityLoginBinding activityLoginBinding;
    private LoginFragment loginFragment;
    public FragmentActivity activity;


    public LoginRegisterViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        repository = new AuthenticationRepository(application);
        userLoginData = repository.getFirebaseLoginUserMutableLiveData();
        //        loadUserDetails();
    }

    public MutableLiveData<FirebaseUser> getUserLoginData() {
        return userLoginData;
    }

    public void getBinding(ActivityMainBinding activityMainBinding) {
        this.activityMainBinding = activityMainBinding;
        repository.getBinding(activityMainBinding);
    }

    public void onRegisterLayoutClick(View view) {
//        Utils.hideSoftKeyboard(activity);
//        Fragment registerFragmentFragment = new RegisterFragment();
//        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frameLayoutContainer, registerFragmentFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
        EventBus.getDefault().postSticky(new RegisterMessage(true));
    }

    public void onForgotPasswordClick(View view) {
        Utils.hideSoftKeyboard(activity);
        Fragment registerFragmentFragment = new ForgotPasswordFragment();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutContainer, registerFragmentFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//    public MutableLiveData<Boolean> onclickHide(View view) {
//        EmailRegisterError.setValue(null);
//        PasswordRegisterError.setValue(null);
//        NameRegisterError.setValue(null);
//        ForgotError.setValue(null);
//
//        EmailRegister.setValue(null);
//        PasswordRegister.setValue(null);
//        NameRegister.setValue(null);
//        ForgotPassword.setValue(null);
//
//        onClickResult.setValue(false);
//        onClickRegister.setValue(false);
//        onClickForgotResult.setValue(false);
//        return onClickResult;
//    }

    public void getFragment(FragmentActivity loginFragment) {
        this.activity = loginFragment;
        repository.getFragment(loginFragment);
    }

//    public MutableLiveData<Boolean> onClickShow(View view) {
//        EmailError.setValue(null);
//        PasswordError.setValue(null);
//        onClickResult.setValue(true);
//        onClickRegister.setValue(true);
//        return onClickResult;
//    }

//    public MutableLiveData<Boolean> onForgotPasswordClick(View view) {
//        EmailError.setValue(null);
//        PasswordError.setValue(null);
//        onClickResult.setValue(true);
//        onClickRegister.setValue(false);
//        onClickForgotResult.setValue(true);
//        return onClickResult;
//    }

    public void onLoginClick(View view) {
        try {
            Utils.hideSoftKeyboard(activity);
            if (Utils.isNetworkConnectionAvailable(activity)) {
                if (validateLogin()) {
                    repository.login(EmailLogin.getValue(), PasswordLogin.getValue());
                    EmailLogin.setValue(null);
                    PasswordLogin.setValue(null);
                }
            } else {
                Toast.makeText(application, "Please check the internet connection", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception exception) {
            Log.e("Error login==> ", "" + exception);
        }
    }

    public void onRegisterClick(View view) {
        try {
            Utils.hideSoftKeyboard(activity);
            if (Utils.isNetworkConnectionAvailable(activity)) {
                if (validateRegister()) {
                    repository.register(EmailRegister.getValue(), PasswordRegister.getValue(), NameRegister.getValue());
                    EmailRegister.setValue(null);
                    PasswordRegister.setValue(null);
                    NameRegister.setValue(null);
                    ForgotPassword.setValue(null);
                }
            } else {
                Toast.makeText(application, "Please check the internet connection", Toast.LENGTH_SHORT).show();
            }
        } catch (
                Exception exception) {
            Log.e("Error register ==> ", "" + exception);
        }
    }

    public void onForgotClick(View view) {
        try {
            Utils.hideSoftKeyboard(activity);
            if (validateForgot()) {
                if (Utils.isNetworkConnectionAvailable(activity)) {
                    repository.forgot(ForgotPassword.getValue());
                    ForgotPassword.setValue(null);
                } else {
                    Toast.makeText(application, "Please check the internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception exception) {
            Log.e("Error forgot==> ", "" + exception);
        }
    }

    public boolean validateLogin() {
        EmailError.setValue(null);
        PasswordError.setValue(null);
        boolean valid = true;
        try {
            if (EmailLogin.getValue() == null || EmailLogin.getValue().isEmpty()) {
                EmailError.setValue("Please enter email address.");
                valid = false;
            }
            if (PasswordLogin.getValue() == null || PasswordLogin.getValue().isEmpty()) {
                PasswordError.setValue("Please enter the password.");
                valid = false;
            }
            if (!isEmailValid(EmailLogin.getValue())) {
                EmailError.setValue("Please enter a valid email address.");
                valid = false;
            }
        } catch (Exception exception) {
            Log.e("Error ==> ", "" + exception);
        }
        return valid;
    }

    public void subscribeBus() {
        EventBus.getDefault().register(this);
    }

    public void unSubscribeBus() {
        EventBus.getDefault().unregister(this);
    }

    public boolean validateRegister() {
        EmailRegisterError.setValue(null);
        NameRegisterError.setValue(null);
        PasswordRegisterError.setValue(null);
        boolean valid = true;
        try {
            if (NameRegister.getValue() == null || NameRegister.getValue().isEmpty()) {
                NameRegisterError.setValue("Please enter the Username");
                valid = false;
            }
            if (EmailRegister.getValue() == null || EmailRegister.getValue().isEmpty()) {
                EmailRegisterError.setValue("Please enter the email");
                valid = false;
            }
            if (PasswordRegister.getValue() == null || PasswordRegister.getValue().isEmpty()) {
                PasswordRegisterError.setValue("Please enter the password.");
                valid = false;
            }
            if (!isEmailValid(EmailRegister.getValue())) {
                EmailRegisterError.setValue("Please enter a valid email address.");
                valid = false;
            }
        } catch (Exception exception) {
            Log.e("Error ==> ", "" + exception);
        }
        return valid;
    }

    public boolean validateForgot() {
        ForgotError.setValue(null);
        boolean valid = true;
        try {
            if (ForgotPassword.getValue() == null || ForgotPassword.getValue().isEmpty()) {
                ForgotError.setValue("Please enter email address.");
                valid = false;
            }
            if (!isEmailValid(ForgotPassword.getValue())) {
                ForgotError.setValue("Please enter a valid email address.");
                valid = false;
            }
        } catch (Exception exception) {
            Log.e("Error ==> ", "" + exception);
        }

        return valid;
    }

    public boolean isEmailValid(String value) {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches();
    }

}
