package com.example.trello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
    }
    public void project (View v){
        Intent intent1 = getIntent();

        String loginpassword = intent1.getStringExtra("password");
        String loginEmail = intent1.getStringExtra("email");

        Intent intent= new Intent(this,project.class);
        intent.putExtra("password", loginpassword);
        intent.putExtra("email", loginEmail);
        startActivity(intent);


    }
    public void showproject(View v){

        Intent intent1 = getIntent();

        String loginpassword = intent1.getStringExtra("password");
        String loginEmail = intent1.getStringExtra("email");

        Intent intent= new Intent(this,projectlist.class);
        intent.putExtra("password", loginpassword);
        intent.putExtra("email", loginEmail);
        startActivity(intent);




    }
    public void logout(View v){

        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void delete (View v){
        Intent intent1 = getIntent();

        String loginpassword = intent1.getStringExtra("password");
        String loginEmail = intent1.getStringExtra("email");

        Intent intent= new Intent(this,Main4Activity.class);
        intent.putExtra("password", loginpassword);
        intent.putExtra("email", loginEmail);
        startActivity(intent);


    }

}
