package com.example.trello;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.trello.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ToDo extends Fragment {
    EditText email;
    EditText password;
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
    String ee;
    String Formatteddate1;
    String bb;
    SearchView assign;
    final Context context = getContext();
    EditText userInput;
    public static ViewPager mv;
    public ToDo() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        dbhelper = new databasehelper(getActivity());
        db = dbhelper.getReadableDatabase();
        mydb = new databasehelper(getActivity());


//        LayoutInflater li = LayoutInflater.from(context);
   //     View promptsView = li.inflate(R.layout.fragment_todo, null);

   //     AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            //    context);





        }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
         email1=this.getArguments().getString("email").toString();
        project=this.getArguments().getString("project").toString();
        View rootView = inflater.inflate(R.layout.fragment_todo, container, false);
        list = (ListView) rootView.findViewById(R.id.list);

        cursor = db.rawQuery("SELECT *FROM todo  WHERE projectname =? AND member=?", new String[]{project, email1});
        final SearchView assign=(SearchView) rootView.findViewById(R.id.assign);
        while(cursor.moveToNext()){
            String a =cursor.getString(2);
            String time=cursor.getString(3);

            if (lol.contains(a)){

            }
            else lol.add(a);
            String d="assignment:"+a+"\nDate:"+time;

            if (list1.contains(d)){

            }
            else list1.add(d);



        }
        arrayAdapter =new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list1);
        list.setAdapter(arrayAdapter);
       SearchView assign1 =(SearchView)rootView.findViewById(R.id.assign) ;
       assign1.setQueryHint("enter assignment = + what you are looking for");

        assign1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public boolean onQueryTextSubmit(String txt) {
                // TODO Auto-generated method stub
                return false;
            }


            @Override
            public boolean onQueryTextChange(String txt) {
                // TODO Auto-generated method stub




                arrayAdapter.getFilter().filter(txt);

              //  getActivity().getActionBar().setIcon(R.drawable.bg_blue);
                return false;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {

              //   mv =(ViewPager)getActivity().findViewById(R.id.b);
              //  onitem();
                Date cc= Calendar.getInstance().getTime();

                SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyy");
                String Formatteddate=df.format(cc);
                b =  arrayAdapter.getItem(position).toString();
                TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.layout);
                tabLayout.getTabAt(1).select();


                String parts []=b.split("\n");


                String j[]=parts[0].split(":");


              mydb.insert3(project,email1,j[1],Formatteddate);


                lol.remove(j[1]);

               list1.remove(b);
            mydb.move(project,email1,j[1]);
            


               // arrayAdapter.notifyDataSetChanged();





            }

        });


     //   b.setOnClickListener(new View.OnClickListener() {      @Override
    //    public void onClick(View view) {
      //      Date cc= Calendar.getInstance().getTime();

      //      SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyy");
     //
        //       String Formatteddate1=df.format(cc);
       //     ee=assign.getText().toString();
         //   String bb = userInput.getText().toString();
      //    mydb.insert2(project,email1,bb,Formatteddate1);
     //     String e="assignment:"+bb+"\nDate:"+Formatteddate1;
       ///   list1.add(e);
       //   lol.add(bb);
        //    arrayAdapter.notifyDataSetChanged();





    //    }  });




        return rootView;
    }


   // public static void onitem(){
  //      mv.setCurrentItem(2);


   // }

    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {

        inflater.inflate(R.menu.menu1,menu);
  super.onCreateOptionsMenu(menu, inflater);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {



            case R.id.item4:
                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                       getActivity());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Date cc = Calendar.getInstance().getTime();

                                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyy");
                                         Formatteddate1 = df.format(cc);
                                        //    ee=assign.getText().toString();
                                         bb = userInput.getText().toString();







                                        cursor = db.rawQuery("SELECT * FROM todo  WHERE projectname=? AND assignment= ? ", new String[]{project,bb});
                                        if (cursor != null) {
                                            if (cursor.getCount() > 0) {


                                                Toast.makeText(getActivity(), "already exists", Toast.LENGTH_SHORT).show();

                                            }
                                            else
                                            {





                                                mydb.insert2(project, email1, bb, Formatteddate1);
                                                String e = "assignment:" + bb + "\nDate:" + Formatteddate1;
                                                list1.add(e);
                                                lol.add(bb);
                                               // MyAsyncTask runner = new MyAsyncTask("donc");
                                               /// runner.execute();
                                                arrayAdapter.notifyDataSetChanged();}
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();




               
                return true;

            case R.id.item2:
                Collections.sort(lol);
                Collections.sort(list1);
                arrayAdapter.notifyDataSetChanged();
                arrayAdapter.notifyDataSetChanged();
                return true;
            case R.id.item3:
                Collections.reverse(list1);
                Collections.sort(this.lol, Collections.reverseOrder());
                Collections.sort(this.list1, Collections.reverseOrder());
                arrayAdapter.notifyDataSetChanged();
        default:
                return  super.onOptionsItemSelected(item);

    }



}

public class MyAsyncTask extends AsyncTask<String, String, Void> {
//Context context;
String c;
    //   MyAsyncTask(Context ctx){context=ctx;}

    public MyAsyncTask(String b) {
        c=b;
        }
protected Void doInBackground(String... params) {





    try { URL url = new URL("http://192.168.1.36/android_login_api/include/insert_project.php?projectname="+ project+"&member="+email+"&assignment="+bb+"&time="+Formatteddate1);
         //URL url = new URL("http://10.0.2.2/android_login_api/include/test.php");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();



            } catch (Exception e) {             System.out.println("Error:   " + e.toString());         }         return null;     }

        @Override
        protected void onProgressUpdate(String... progress) {         super.onProgressUpdate();



        }

    }




}
