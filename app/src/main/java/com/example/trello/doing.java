package com.example.trello;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trello.R;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class doing extends Fragment {
    EditText assign;
    databasehelper mydb;
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    ArrayList<String> lol=new ArrayList<String>();
    ArrayList<String>list1=new ArrayList<>();
    ArrayAdapter <String> arrayAdapter;
    ListView list ;
    String email1;
    String project;
    String b;

    public doing() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        dbhelper = new databasehelper(getActivity());
        db = dbhelper.getReadableDatabase();
        mydb = new databasehelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_doing, container, false);
        email1=this.getArguments().getString("email").toString();
        project=this.getArguments().getString("project").toString();

        list = (ListView) rootView.findViewById(R.id.list);

        cursor = db.rawQuery("SELECT *FROM doing  WHERE projectname =? AND email=?", new String[]{project, email1});

        while(cursor.moveToNext()){
            String a =cursor.getString(2);
            String time=cursor.getString(3);

            if (lol.contains(a)){

            }
            else lol.add(a);
            String d="assignment:"+a+"\n Date:"+time;

            if (list1.contains(d)){

            }
            else list1.add(d);



        }
        arrayAdapter =new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list1);
        list.setAdapter(arrayAdapter);
        SearchView assign1 =(SearchView)rootView.findViewById(R.id.assign) ;
        assign1.setQueryHint("enter assignment = + what you are looking for");
        assign1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String txt) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onQueryTextChange(String txt) {
                // TODO Auto-generated method stub

                arrayAdapter.getFilter().filter(txt);


                return false;
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {

                Date cc= Calendar.getInstance().getTime();
                TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.layout);
                tabLayout.getTabAt(2).select();
                SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyy");
                String Formatteddate=df.format(cc);
                b =  arrayAdapter.getItem(position).toString();



                String parts []=b.split("\n");


                String j[]=parts[0].split(":");


                mydb.insert4(project,email1,j[1],Formatteddate);


                lol.remove(j[1]);

                list1.remove(b);
                mydb.move1(project,email1,j[1]);

            //    arrayAdapter.notifyDataSetChanged();
                return false;
            }

        });



        return rootView;
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){





        case R.id.item2:
                Collections.sort(lol);
                Collections.sort(list1);
                arrayAdapter.notifyDataSetChanged();
                return true;
            case R.id.item3:
                Collections.reverse(list1);
                Collections.sort(this.lol, Collections.reverseOrder());
                Collections.sort(this.list1, Collections.reverseOrder());
                arrayAdapter.notifyDataSetChanged();
                return true;
            default:
                return  super.onOptionsItemSelected(item);

        }

    }

// Inflate the layout for this fragment

}