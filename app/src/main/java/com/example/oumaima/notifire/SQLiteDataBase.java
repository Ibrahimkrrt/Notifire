package com.example.oumaima.notifire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


/*
 * DataBase Management with SQLite
 * */
public class SQLiteDataBase extends SQLiteOpenHelper {

    public static final String dataBaseName = "NOTIFIRE.DB";
    public static final String usersTableName = "USERS";
    public static final String marksTableName = "MARKS";

    public SQLiteDataBase(Context context) {
        super(context, dataBaseName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ usersTableName+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "EMAIL TEXT UNIQUE, " +
                "PASSWORD TEXT, " +
                "NAME TEXT, " +
                "SURNAME TEXT, " +
                "MARKID INTEGER, " +
                "USERNAME TEXT, " +
                "PASSWORD TEXT, " +
                "SCHOOL TEXT, " +
                "LEVEL TEXT, " +
                "STUDENTCLASS TEXT, " +
                "STUDENTGROUP TEXT, " +
                "ADMIN BOOLEAN)");
        db.execSQL("CREATE TABLE "+ marksTableName+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENTMARK TEXT, MARKDATE DATE, ADMINID INTEGER)");
        Log.d("onCreacte", "values : creacted");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+usersTableName);
        db.execSQL("DROP TABLE IF EXISTS "+marksTableName);
        onCreate(db);
    }

    public void Recreate() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+usersTableName);
        db.execSQL("DROP TABLE IF EXISTS "+marksTableName);
        db.execSQL("CREATE TABLE "+ usersTableName+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKID INTEGER, EMAIL TEXT UNIQUE, PASSWORD TEXT, ADMIN BOOLEAN)");
        db.execSQL("CREATE TABLE "+ marksTableName+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENTMARK TEXT, MARKDATE DATE, ADMINID INTEGER)");
        Log.d("onCreacte", "values : creacted");

    }

    /// <summary>
    /// User SQLite actions
    /// </summary>
    public boolean insetNewStudent(String email, String password, String name, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("inInsert", "values : " + name + " , " +surname);
        contentValues.put("EMAIL", email);
        contentValues.put("PASSWORD", password);
        contentValues.put("NAME", name);
        contentValues.put("SURNAME", surname);
        contentValues.put("ADMIN", false);
        long resolver = db.insert(usersTableName,null, contentValues);
        Log.d("inInsert", "resolver value : " + resolver);
        if (resolver == -1 ) return false;
        return true;
    }

    public boolean insetNewAdmin(String email, String password, String name, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("PASSWORD", password);
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

    public boolean updateStudentMarkId(String studentId,String markId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MARKID",markId);
        long resolver =db.update(usersTableName,contentValues,"ID = " +studentId,null);
        if (resolver == -1 ) return false;
        return true;
    }

    public boolean login(String email,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resolver = db.rawQuery("SELECT * FROM "+ usersTableName + " WHERE EMAIL  = " + email + " AND PASSWORD = " + password,null);
        Log.d("SQLite : ", " " + resolver.getColumnCount());
        // if ( resolver.getColumnCount() ) return true;
        return true;
    }

    /// <summary>
    /// Marks SQLite actions
    /// </summary>
    public long insetNewMark(String mark, int adminId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Log.d("inInsert", "values : " + mark + " , "+ dateFormat.format(today) + " , " +adminId);
        contentValues.put("STUDENTMARK", mark);
        contentValues.put("MARKDATE", dateFormat.format(today));
        contentValues.put("ADMINID", adminId);
        long resolver = db.insert(marksTableName,null, contentValues);
        Log.d("inInsert", "resolver value : " + resolver);
        return resolver;
    }

    public Cursor findMarkById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resolver = db.rawQuery("SELECT * FROM "+ marksTableName + " WHERE ID = "+ id,null);
        return resolver;
    }

}

