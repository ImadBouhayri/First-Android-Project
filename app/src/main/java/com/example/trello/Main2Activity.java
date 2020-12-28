package com.example.trello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class Main2Activity extends AppCompatActivity {
String project;
String loginEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent1 = getIntent();

        project = intent1.getStringExtra("project");
        loginEmail = intent1.getStringExtra("email");
        System.out.println(project);

        System.out.println(loginEmail);

       // FrameLayout simpleFrameLayout = (FrameLayout) findViewById(R.id.simpleFrameLayout);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.layout);

        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("To Do");

        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Doing");

        tabLayout.addTab(secondTab);

        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("Done");

        tabLayout.addTab(thirdTab);


// perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ToDo();
                        break;
                    case 1:
                        fragment = new doing();
                        break;
                    case 2:
                        fragment = new done();
                        break;
                    default:
                        fragment = new ToDo();
                        break;
                }

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                Bundle args = new Bundle();
                args.putString("email", loginEmail);
                args.putString("project", project);
                fragment.setArguments(args);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab taab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        FragmentManager fm = getSupportFragmentManager();
       FragmentTransaction ft1 = fm.beginTransaction();
        Bundle args = new Bundle();
        args.putString("email", loginEmail);
        args.putString("project", project);
        Fragment fragment11= new ToDo();
      fragment11.setArguments(args);

        ft1.add(R.id.simpleFrameLayout, fragment11);
        ft1.commit();
    }


    }





