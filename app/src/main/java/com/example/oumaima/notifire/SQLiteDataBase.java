package com.example.oumaima.notifire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/*
 * DataBase Management with SQLite
 * */
public class SQLiteDataBase extends SQLiteOpenHelper {

    public static final String dataBaseName = "NOTIFIRE.DB";
    public static final String usersTableName = "USERS";
    public static final String marksTableName = "MARKS";
    public static final String adminsTableName = "ADMINS";

    public SQLiteDataBase(Context context) {
        super(context, dataBaseName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ usersTableName+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKID INTEGER, USERNAME TEXT, PASSWORD TEXT, ADMIN BOOLEAN)");
        db.execSQL("CREATE TABLE "+ usersTableName+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENTMARK TEXT, MARKDATE DATE, ADMINID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+usersTableName);
        db.execSQL("DROP TABLE IF EXISTS "+marksTableName);
        onCreate(db);
    }


    public boolean insetNewStudent(String name, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("inInsert", "values : " + name + " , " +surname);
        contentValues.put("NAME", name);
        contentValues.put("SURNAME", surname);
        contentValues.put("ADMIN", false);
        long resolver = db.insert(usersTableName,null, contentValues);
        Log.d("inInsert", "resolver value : " + resolver);
        if (resolver == -1 ) return false;
        return true;
    }

    public boolean insetNewAdmin(String name, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("SURNAME", surname);
        contentValues.put("ADMIN", true);
        long resolver = db.insert(usersTableName,null, contentValues);
        if (resolver == -1 ) return false;
        return true;
    }

    public Cursor selectAllStudent() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resolver = db.rawQuery("SELECT * FROM "+ usersTableName + " WHERE NOT ADMIN",null);
        return resolver;
    }
}

