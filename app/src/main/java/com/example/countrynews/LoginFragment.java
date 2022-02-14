package com.example.countrynews;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.countrynews.databinding.FragmentLoginBinding;
import com.example.countrynews.utils.Utils;
import com.example.countrynews.viewModel.LoginRegisterViewModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    //    FragmentLoginBinding fragmentLoginBinding;
    private LoginRegisterViewModel loginRegisterViewModel;
    private FragmentLoginBinding fragmentLoginBinding;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
////        fragmentLoginBinding.setLoginRegisterViewModel(loginRegisterViewModel);
//        View view=fragmentLoginBinding.getRoot();
//        return inflater.inflate(R.layout.fragment_register, container, false);
//        Utils.hideSoftKeyboard(getActivity());
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
//            loginRegisterViewModel.getUserLoginData().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
//                @Override
//                public void onChanged(FirebaseUser firebaseUser) {
//                    Fragment fragment = new NewsFragment();
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.frameLayoutContainer, fragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                }
//            });
        } catch (Exception exception) {
            Log.e("Error ==> ", "" + exception);

        }
    }


}