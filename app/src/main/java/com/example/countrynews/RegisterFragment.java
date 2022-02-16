package com.example.countrynews;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.countrynews.broadcast.MyBroadcastReceiver;
import com.example.countrynews.databinding.FragmentRegisterBinding;
import com.example.countrynews.viewModel.LoginRegisterViewModel;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding fragmentRegisterBinding;
    private LoginRegisterViewModel loginRegisterViewModel;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                Toast.makeText(getActivity(), "Back Pressed", Toast.LENGTH_SHORT).show();
////                fragmentLoginBinding.layoutLoginPage.setVisibility(View.GONE);
//                Fragment loginFragment = new LoginFragment();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.layoutLogin, loginFragment);
////                transaction.addToBackStack(null);
//                    transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginRegisterViewModel = new ViewModelProvider(requireActivity()).get(LoginRegisterViewModel.class);
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(getLayoutInflater());
        fragmentRegisterBinding.setLoginregisterViewModel(loginRegisterViewModel);
        fragmentRegisterBinding.setLifecycleOwner(this);
        loginRegisterViewModel.getFragment(getActivity());
        // Inflate the layout for this fragment
        return fragmentRegisterBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentRegisterBinding.layoutSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                fragmentLoginBinding.layoutLoginPage.setVisibility(View.GONE);
                Fragment registerFragmentFragment = new RegisterFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layoutLogin, registerFragmentFragment, "Register");
                transaction.addToBackStack(null);
//                    transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}