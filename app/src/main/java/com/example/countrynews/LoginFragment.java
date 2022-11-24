package com.example.countrynews;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.countrynews.databinding.FragmentLoginBinding;
import com.example.countrynews.messages.BaseMessage;
import com.example.countrynews.messages.LoginMessages;
import com.example.countrynews.messages.NewsMessage;
import com.example.countrynews.utils.Utils;
import com.example.countrynews.viewModel.LoginRegisterViewModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class LoginFragment extends BaseFragment {

    //    FragmentLoginBinding fragmentLoginBinding;
    private LoginRegisterViewModel loginRegisterViewModel;
    private FragmentLoginBinding fragmentLoginBinding;
    private LoginMessages message;


    public LoginFragment(LoginMessages message) {
        // Required empty public constructor
        this.message = message;
        Log.e("LoginMessages", "" + message.showBack);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseApp.initializeApp(getActivity());
        loginRegisterViewModel = new ViewModelProvider(requireActivity()).get(LoginRegisterViewModel.class);
        fragmentLoginBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        fragmentLoginBinding.setLoginregisterViewModel(loginRegisterViewModel);
        fragmentLoginBinding.setLifecycleOwner(this);
        loginRegisterViewModel.getFragment(getActivity());
        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Utils.hideSoftKeyboard(getActivity());
            loginRegisterViewModel.getUserLoginData().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
                @Override
                public void onChanged(FirebaseUser firebaseUser) {
//                    Fragment fragment = new NewsFragment();
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.frameLayoutContainer, fragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
                    showDialogWithSingleButton("Success!", "Login successfully", "ok", new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            EventBus.getDefault().postSticky(new NewsMessage(false));
                            dialog.dismiss();
                        }
                    });
                }
            });
        } catch (Exception exception) {
            Log.e("Error ==> ", "" + exception);

        }
    }

    @Subscribe
    public void loadFragment(BaseMessage message) {
        Log.e("loadFragment", "Called");
//        if (message != null) {
//            switch (message.getFragment()) {
//                case Constant.FRAGMENT_LOGIN:
//                    fragment = new LoginFragment();
//                    break;
//                case Constant.FRAGMENT_REGISTER:
//                    fragment = new RegisterFragment();
//            }
//            if (fragment != null) {
//                getSupportFragmentManager().beginTransaction()
//                        .remove(fragment).commit();
//                getSupportFragmentManager().popBackStack();
//                getSupportFragmentManager().beginTransaction()
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .addToBackStack(null)
//                        .add(R.id.frameLayoutContainer, fragment).commit();
//            }
//        } else {
//            Log.e("Message", "Null");
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        org.greenrobot.eventbus.EventBus.getDefault().register(this);
        Log.e("LifeCycle News ==>", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        Log.e("LifeCycle News ==>", "OnPause");
    }
}