package com.example.trello;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class databasehelper extends SQLiteOpenHelper {
    public static final String Database_Name = "trello.db";
    public static final String Table_name = "members_table";
    protected static final String mem = "members";

    public static final String col1 = "ID";
    public static final String col2 = "firstname";
    public static final String col3 = "lastname";
    public static final String col4 = "password";
    public static final String col5 = "email";


    public static final String table1 = "member";
    public static final String Key1_firstname = "firstname";
    public static final String Key2_lastname = "lastname";
    public static final String Key3_password = "password";
    public static final String Key4_email = "email";

    public static final String table2 = "projects";
    public static final String Key5_projectname = "projectname";
    public static final String Key6_email = "email";

    public static final String table3= "todo";
    public static final String Key7_projectname = "projectname";
    public static final String Key8_email = "member";
    public static final String Key9_assignment = "assignment";
    public static final String Key10_time = "time";

    public static final String table4= "doing";
    public static final String Key11_projectname = "projectname";
    public static final String Key12_email = "email";
    public static final String Key13_assignment = "assignment";
    public static final String Key14_time = "time";

    public static final String table5= "done";
    public static final String Key15_projectname = "project";
    public static final String Key16_email = "member";
    public static final String Key17_assignment = "assignment";
    public static final String Key18_time = "time";

    public databasehelper(@Nullable Context context) {
        super(context, Database_Name, null, 9);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String command =
                String.format("CREATE TABLE %s(%s TEXT,%s TEXT,%s TEXT,%s TEXT,PRIMARY KEY(%s))", table1, Key1_firstname, Key2_lastname, Key3_password, Key4_email, Key4_email);
        String command1 =
                String.format("CREATE TABLE %s(%s TEXT,%s TEXT,PRIMARY KEY(%s),FOREIGN KEY(%s) REFERENCES %s(%s))", table2, Key5_projectname, Key6_email,  Key5_projectname,Key6_email,table1,Key4_email);
        String command2 =
                String.format("CREATE TABLE %s(%s TEXT,%s TEXT,%s TEXT,%s TEXT,FOREIGN KEY(%s) REFERENCES %s(%s),FOREIGN KEY(%s) REFERENCES %s(%s))", table3, Key7_projectname, Key8_email,  Key9_assignment,Key10_time,Key8_email,table1,Key4_email,Key7_projectname,table2,Key5_projectname);
        String command3 =
                String.format("CREATE TABLE %s(%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,FOREIGN KEY(%s) REFERENCES %s(%s),FOREIGN KEY(%s) REFERENCES %s(%s))", table4, Key11_projectname, Key12_email,  Key13_assignment,Key14_time,Key12_email,table1,Key4_email,Key11_projectname,table2,Key5_projectname);
        String command4 =
                String.format("CREATE TABLE %s(%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,FOREIGN KEY(%s) REFERENCES %s(%s),FOREIGN KEY(%s) REFERENCES %s(%s))", table5, Key15_projectname, Key16_email,  Key17_assignment,Key18_time,Key16_email,table1,Key4_email,Key15_projectname,table2,Key5_projectname);
        //  String command1 = "CREATE TABLE member( ID INTEGER PRIMARY KEY AUTOINCREMENT,first_name TEXT ,last_name TEXT,password TEXT,email TEXT )";
        // String command1 = "CREATE TABLE grp( ID INTEGER PRIMARY KEY AUTOINCREMENT,grp_name TEXT ,gr_leader TEXT,gr_member TEXT)";
        db.execSQL(command);
        db.execSQL(command1);
        db.execSQL(command2);
        db.execSQL(command3);
        db.execSQL(command4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS members ");

        onCreate(db);
    }

    public boolean insertdata(String firstname, String lastname, String password, String email) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(col2, firstname);
        contentvalues.put(col3, lastname);
        contentvalues.put(col4, password);
        contentvalues.put(col5, email);
        long result = db.insert("member", null, contentvalues);
        if (result == -1)
            return false;
        else return true;

    }

    public boolean insertdata_project( String email, String projectname) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();

        contentvalues.put("projectname", projectname);
        contentvalues.put("email", email);
        long result = db.insert("projects", null, contentvalues);
        if (result == -1)
            return false;
        else return true;

    }


    public boolean insert2(String projectname, String member, String assignmen,String time) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("projectname" ,projectname);
        contentvalues.put("member", member);
        contentvalues.put("assignment", assignmen);
        contentvalues.put("time",time);
        long result = db.insert("todo", null, contentvalues);
        if (result == -1) {
            return false;
        } else return true;

    }
    public boolean insert3(String projectname, String member, String assignment,String time) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("projectname" ,projectname);
        contentvalues.put("email", member);
        contentvalues.put("assignment", assignment);
        contentvalues.put("time",time);
        long result = db.insert("doing", null, contentvalues);
        if (result == -1) {
            return false;
        } else return true;

    }
    public boolean insert4(String projectname, String member, String assignmen,String time) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("project" ,projectname);
        contentvalues.put("member", member);
        contentvalues.put("assignment", assignmen);
        contentvalues.put("time",time);
        long result = db.insert("done", null, contentvalues);
        if (result == -1) {
            return false;
        } else return true;

    }

    public Cursor getListContents(String k) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM projects where email='" + k + "'", null);

        return data;

    }

    public Cursor gettodo(String email,String project) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM todo where projectname='"+project+"' member='"+email+"'", null);

        return data;

    }

    public Cursor showmembers(String k) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM grp where gr_name= '" + k + "'", null);

        return cursor;


    }

    public boolean move(String project, String email,String assignment) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("todo", "projectname = '" + project + "' AND member = '" + email + "'AND assignment='"+assignment+"'", null) > 0;
    }
    public boolean move1(String project, String email,String assignment) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("doing", "projectname = '" + project + "' AND email = '" + email + "'AND assignment='"+assignment+"'", null) > 0;
    }
    public boolean move2(String project, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("projects", "projectname = '" + project + "' AND email = '" + email+"'", null) > 0;
    }
    public boolean move3(String project, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("todo", "projectname = '" + project + "' AND member = '" + email+"'", null) > 0;
    }
    public boolean move4(String project, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("doing", "projectname = '" + project + "' AND email = '" + email+"'", null) > 0;
    }
    public boolean move5(String project, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("done", "project = '" + project + "' AND member = '" + email+"'", null) > 0;
    }



    public boolean insert_assign(String groupname, String groupleader, String members, String task) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("group_name", groupname);
        contentvalues.put("grp_leader", groupleader);
        contentvalues.put("grp_member", members);
        contentvalues.put("assignment", task);
        long result = db.insert("assignments", null, contentvalues);
        if (result == -1) {
            return false;
        } else return true;

    }

    public Cursor show_members_groups(String k) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM grp where gr_member = '" + k + "'", null);

        return cursor;
    }

    public Cursor get_assignments(String k) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM assignments where grp_member = '" + k + "'", null);

        return cursor;


    }
}



