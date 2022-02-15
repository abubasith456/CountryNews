package com.example.countrynews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.countrynews.databinding.FragmentForgotPasswordBinding;
import com.example.countrynews.viewModel.ForgotPasswordViewModel;
import com.example.countrynews.viewModel.LoginRegisterViewModel;

public class ForgotPasswordFragment extends Fragment {

    private FragmentForgotPasswordBinding fragmentForgotPasswordBinding;
    private LoginRegisterViewModel loginRegisterViewModel;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentForgotPasswordBinding = FragmentForgotPasswordBinding.inflate(getLayoutInflater());
        loginRegisterViewModel = new ViewModelProvider(requireActivity()).get(LoginRegisterViewModel.class);
        fragmentForgotPasswordBinding.setLoginregisterViewModel(loginRegisterViewModel);
        fragmentForgotPasswordBinding.setLifecycleOwner(this);
        // Inflate the layout for this fragment
        return fragmentForgotPasswordBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}