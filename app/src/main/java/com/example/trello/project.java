package com.example.trello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class project extends AppCompatActivity {
    databasehelper mydb;
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    EditText t1;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        dbhelper = new databasehelper(this);
        db = dbhelper.getReadableDatabase();
        mydb = new databasehelper(this);
    }

    public void register_project(View v){
        Intent intent = getIntent();
        String loginpassword = intent.getStringExtra("password");
        String loginEmail1 = intent.getStringExtra("email");
        EditText project=(EditText)findViewById(R.id.project);
         String b=project.getText().toString();
        if (b.equals("")) {
            Toast.makeText(this, "enter project name", Toast.LENGTH_SHORT).show();
        } else {


            cursor = db.rawQuery("SELECT * FROM projects  WHERE projectname=? AND email= ? ", new String[]{b,loginEmail1});
            if (cursor != null) {
                if (cursor.getCount() > 0) {


                        Toast.makeText(this, "project already exists", Toast.LENGTH_SHORT).show();

                    }
                else {
                boolean isinserted = mydb.insertdata_project(loginEmail1, b);
                if (isinserted == true) {
                    Intent intent1 = getIntent();

                    String loginpassword1 = intent1.getStringExtra("password");
                    String loginEmail = intent1.getStringExtra("email");

               ////     Intent intent11= new Intent(this,Dash.class);
              //      intent.putExtra("password", loginpassword1);
              //      intent.putExtra("email", loginEmail);
               //     startActivity(intent11);
                    Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();




                    //-------Alert Dialog Code Snippet End Here
                }
            }

            }

        }

    }
}
