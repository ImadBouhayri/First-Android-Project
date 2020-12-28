package com.example.trello;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    EditText text1;
    EditText text2;
    EditText text3;
    EditText text4;

    EditText email;
    EditText password;
    databasehelper mydb;
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;

    // JSON Node names


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        text1 = (EditText) findViewById(R.id.text1);
        text2 = (EditText) findViewById(R.id.text2);
        text3 = (EditText) findViewById(R.id.text3);
        text4 = (EditText) findViewById(R.id.text4);
        dbhelper = new databasehelper(this);
        db = dbhelper.getReadableDatabase();
        mydb = new databasehelper(this);
        email = (EditText) findViewById(R.id.text4);
        password = (EditText) findViewById(R.id.text3);

    }

    public void register(View v) {
        String a = text1.getText().toString();
        String b = text2.getText().toString();
        String c = text3.getText().toString();
        String d = text4.getText().toString();


        String email1 = email.getText().toString();
        String pass = password.getText().toString();
        String Expn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        String Expn1 ="(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[!?@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +              ////at least 4 characters
                "$";
        if (d.matches(Expn) && d.length() > 0) {
            if (c.matches(Expn1) && c.length() > 0) {
                cursor = db.rawQuery("SELECT *FROM member  WHERE email =? AND password=?", new String[]{email1, pass});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {


                        Toast.makeText(signup.this, "User already exists", Toast.LENGTH_SHORT).show();

                    } else {
                        boolean isinserted = mydb.insertdata(a, b, c, d);
                        if (isinserted == true) {

                            Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                            startActivity(intent);


                        }
                        //I am showing Alert Dialog Box here for alerting user about wrong credentials

                        //-------Alert Dialog Code Snippet End Here
                    }


                }


            }
            else Toast.makeText(signup.this, "password should have at least 1 special character ,no white spaces,at least 4 characters ", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(signup.this, "invalid email", Toast.LENGTH_SHORT).show();
    }


}
