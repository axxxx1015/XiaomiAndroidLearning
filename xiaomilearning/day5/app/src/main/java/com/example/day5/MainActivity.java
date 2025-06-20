package com.example.day5;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnItemSelectedListener(item -> {
            Fragment fragment;
            if (item.getItemId() == R.id.menu_search) {
                fragment = new SearchFragment();
            } else {
                fragment = new LocalDataFragment();
            }
            switchFragment(fragment);
            return true;
        });
        // 默认显示搜索页
        switchFragment(new SearchFragment());
    }
    private void switchFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }
} 