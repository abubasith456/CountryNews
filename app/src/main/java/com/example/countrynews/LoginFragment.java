package com.example.countrynews;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.countrynews.databinding.FragmentLoginBinding;
import com.example.countrynews.viewModel.LoginRegisterViewModel;


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
        loginRegisterViewModel = new ViewModelProvider(requireActivity()).get(LoginRegisterViewModel.class);
        fragmentLoginBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        fragmentLoginBinding.setLoginregisterViewModel(loginRegisterViewModel);
        fragmentLoginBinding.setLifecycleOwner(this);
        loginRegisterViewModel.getFragment(this);
        return fragmentLoginBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            fragmentLoginBinding.layoutRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RegisterFragment registerFragmentFragment = new RegisterFragment();

                    FragmentTransaction transaction= getFragmentManager().beginTransaction();
                    transaction.replace(R.id.layoutLoginPage,registerFragmentFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        } catch (Exception exception) {
            Log.e("Error ==> ", "" + exception);

        }
    }
}