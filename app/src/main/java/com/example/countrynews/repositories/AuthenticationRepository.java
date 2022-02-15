package com.example.countrynews.repositories;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

import com.example.countrynews.NewsFragment;
import com.example.countrynews.R;
import com.example.countrynews.databinding.ActivityMainBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AuthenticationRepository {
    private Application application;
    private MutableLiveData<FirebaseUser> firebaseLoginUserMutableLiveData;
    private MutableLiveData<Boolean> onShowIcon;
    private MutableLiveData<Boolean> userLoggedMutableLiveData;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private FragmentActivity fragmentActivity;
    ActivityMainBinding activityMainBinding;


    public MutableLiveData<FirebaseUser> getFirebaseLoginUserMutableLiveData() {
        return firebaseLoginUserMutableLiveData;
    }

    public MutableLiveData<Boolean> getOnShowIcon(){
        return onShowIcon;
    }

    public MutableLiveData<Boolean> getUserLoggedMutableLiveData() {
        return userLoggedMutableLiveData;
    }

    public AuthenticationRepository(Application application) {
        this.application = application;
        firebaseLoginUserMutableLiveData = new MutableLiveData<>();
        userLoggedMutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void getFragment(FragmentActivity loginFragment) {
        this.fragmentActivity = loginFragment;
    }

    public void getBinding(ActivityMainBinding activityMainBinding) {
        this.activityMainBinding = activityMainBinding;
    }

    public void register(String email, String pass, String name) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Fragment fragment = new NewsFragment();
                    FragmentTransaction fragmentTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayoutContainer, fragment);
                    fragmentTransaction.addToBackStack("Login");
                    fragmentTransaction.commit();
                    storeUserInputData(name, email);
                } else {
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void login(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Fragment fragment = new NewsFragment();
                    FragmentTransaction fragmentTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayoutContainer, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    firebaseLoginUserMutableLiveData.postValue(auth.getCurrentUser());
                } else {
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void forgot(String email) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(application.getApplicationContext(), "Link sent to your email", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(application.getApplicationContext(), "" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void storeUserInputData(String name, String email) {

        HashMap<String, Object> addFieldInfo = new HashMap<>();
        addFieldInfo.put("userId", "" + auth.getCurrentUser().getUid());
        addFieldInfo.put("userEmailAddress", "" + email);
        addFieldInfo.put("userName", "" + name);
        DocumentReference databaseReference = firebaseFirestore.collection("Users").document(auth.getCurrentUser().getUid());
        databaseReference.set(addFieldInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(application.getApplicationContext(), "User data uploaded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application.getApplicationContext(), "" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    public void signOut() {
//        try {
//            AlertDialog.Builder builder1 = new AlertDialog.Builder(application.getApplicationContext());
//            builder1.setMessage("Are you sure.. do you want to logout?");
//            builder1.setCancelable(true);
//
//            builder1.setPositiveButton(
//                    "Yes",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            auth.signOut();
//                            Intent intent = new Intent(application.getApplicationContext(), LoginRegisterActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            application.startActivity(intent);
//                        }
//                    });
//
//            builder1.setNegativeButton(
//                    "No",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.cancel();
//                        }
//                    });
//
//            AlertDialog alert11 = builder1.create();
//            alert11.show();
//        } catch (Exception exception) {
//            Log.e("Error ==> ", "" + exception);
//        }
//        userLoggedMutableLiveData.postValue(true);
//    }

}
