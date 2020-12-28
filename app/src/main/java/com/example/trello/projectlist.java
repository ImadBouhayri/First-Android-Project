package com.example.trello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class projectlist extends AppCompatActivity {
    String loginEmail;
    databasehelper mydb;
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> projects = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectlist);
        ListView r = (ListView)findViewById(R.id.List1);
        dbhelper = new databasehelper(this);
        db = dbhelper.getReadableDatabase();
        mydb = new databasehelper(this);
        Intent intent1 = getIntent();
         loginEmail = intent1.getStringExtra("email");
         System.out.print(loginEmail);
        Cursor data = mydb.getListContents(loginEmail);
        if(data.getCount() == 0){

            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();

        }else{

            while(data.moveToNext()){
                String a =data.getString(0);
                String b =data.getString(1);

                if (projects.contains(a)){

                }
                else projects.add(a);
                String d=" project name:"+a;
                if (list.contains(d)){

                }
                else list.add(d);



            }
          ArrayAdapter  listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            r.setAdapter(listAdapter);
            db.close();
        }
        r.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {
                ;








                String b =projects.get(position);
                System.out.println(b);
                System.out.println(loginEmail);
                showmembers(loginEmail,b);

                return false;
            }

        });





        }
    public void showmembers(String a,String b){
        Intent intent1 = new Intent(this,Main2Activity.class);
        intent1.putExtra("email",a);
        intent1.putExtra("project",b);
        startActivity(intent1);
    }

}
