package com.example.countrynews.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.countrynews.NewsFragment;
import com.example.countrynews.utils.Utils;
import com.example.countrynews.viewModel.NewsFragmentViewModel;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (isOnline(context)) {
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Please check the internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isOnline(Context context) {
        if (Utils.isNetworkConnectionAvailable(context)) {
            return true;
        } else {
            return false;
        }
    }
}
