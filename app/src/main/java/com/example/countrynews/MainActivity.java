package com.example.countrynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.countrynews.broadcast.MyBroadcastReceiver;
import com.example.countrynews.databinding.ActivityMainBinding;
import com.example.countrynews.utils.Utils;
import com.example.countrynews.viewModel.LoginRegisterViewModel;
import com.example.countrynews.viewModel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    public MyBroadcastReceiver myBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        activityMainBinding.setMainActivityViewModel(mainActivityViewModel);
        mainActivityViewModel.getActivity(MainActivity.this);
        myBroadcastReceiver = new MyBroadcastReceiver();
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
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver(myBroadcastReceiver, filter);
        checkExistUser();
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