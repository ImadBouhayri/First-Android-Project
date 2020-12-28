package com.example.trello;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    databasehelper mydb;
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper = new databasehelper(this);
        db = dbhelper.getReadableDatabase();
        mydb = new databasehelper(this);
        email=(EditText)findViewById(R.id.em);
        password=(EditText)findViewById(R.id.password);

    }
    public void signup (View v){



        Intent intent= new Intent(this,signup.class);
        startActivity(intent);

    }
public void signin(View v){
    String email1 = email.getText().toString();
    String pass = password.getText().toString();
    cursor = db.rawQuery("SELECT *FROM member  WHERE email =? AND password=?", new String[]{email1, pass});
    if (cursor != null) {
        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            //Retrieving User FullName and Email after successfull login and passing to LoginSucessActivity
            String passw = cursor.getString(cursor.getColumnIndex(databasehelper.col4));
            String _email = cursor.getString(cursor.getColumnIndex(databasehelper.col5));
            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Dash.class);
            intent.putExtra("fullname", passw);
            intent.putExtra("email", _email);
            startActivity(intent);

            //Removing MainActivity[Login Screen] from the stack for preventing back button press.
            finish();
        } else {

            //I am showing Alert Dialog Box here for alerting user about wrong credentials
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Alert");
            builder.setMessage("Username or Password is wrong.");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
            //-------Alert Dialog Code Snippet End Here
        }


    }



}
    }

