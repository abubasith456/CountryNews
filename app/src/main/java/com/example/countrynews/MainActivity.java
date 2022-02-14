package com.example.countrynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutContainer,new LoginFragment(),"Login")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.more_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sportsNews:
                Toast.makeText(this, "Sports news clicked", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutContainer,new SportsFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.offlineView:
                Toast.makeText(this, "Offline news clicked", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutContainer,new OfflineNewsFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}