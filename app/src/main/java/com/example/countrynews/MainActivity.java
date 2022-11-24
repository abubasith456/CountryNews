package com.example.countrynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.countrynews.broadcast.MyBroadcastReceiver;
import com.example.countrynews.databinding.ActivityMainBinding;
import com.example.countrynews.messages.BaseMessage;
import com.example.countrynews.messages.LoginMessages;
import com.example.countrynews.messages.RegisterMessage;
import com.example.countrynews.utils.Constant;
import com.example.countrynews.utils.Utils;
import com.example.countrynews.viewModel.LoginRegisterViewModel;
import com.example.countrynews.viewModel.MainActivityViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    public MyBroadcastReceiver myBroadcastReceiver;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        activityMainBinding.setMainActivityViewModel(mainActivityViewModel);
        mainActivityViewModel.getActivity(MainActivity.this);
        myBroadcastReceiver = new MyBroadcastReceiver();
        EventBus.getDefault().post(new LoginMessages(false));
    }

    private void checkExistUser() {
        try {
            mainActivityViewModel.checkExistUser();
        } catch (Exception e) {
            Log.e("Error==> ", e.getMessage());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver(myBroadcastReceiver, filter);
        checkExistUser();
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    //Event bus to listener the event
    @Subscribe
    public void loadFragment(BaseMessage message) {
        Log.e("loadFragment", "Called");
        if (message != null) {
            switch (message.getFragment()) {
                case Constant.FRAGMENT_LOGIN:
                    fragment = new LoginFragment((LoginMessages) message);
                    break;
                case Constant.FRAGMENT_REGISTER:
                    fragment = new RegisterFragment();
                    break;
                case Constant.FRAGMENT_NEWS:
                    fragment=new NewsFragment();
                    break;
            }
            if (fragment != null) {
                if (fragment instanceof RegisterFragment) {
                    getSupportFragmentManager().beginTransaction()
                            .remove(fragment).commit();
                    getSupportFragmentManager().popBackStack();
                    getSupportFragmentManager().beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .addToBackStack(null)
                            .add(R.id.frameLayoutContainer, fragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .remove(fragment).commit();
                    getSupportFragmentManager().popBackStack();
                    getSupportFragmentManager().beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null)
                            .add(R.id.frameLayoutContainer, fragment).commit();
                }
            }
        } else {
            Log.e("Message", "Null");
        }
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.more_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.sportsNews:
//                Toast.makeText(this, "Sports news clicked", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutContainer, new SportsFragment())
//                        .addToBackStack(null)
//                        .commit();
//                return true;
//            case R.id.offlineView:
//                Toast.makeText(this, "Offline news clicked", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutContainer, new OfflineNewsFragment())
//                        .addToBackStack(null)
//                        .commit();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

}