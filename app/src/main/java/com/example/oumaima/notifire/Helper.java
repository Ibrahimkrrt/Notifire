package com.example.oumaima.notifire;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.oumaima.notifire.models.Student;

import java.util.ArrayList;
import java.util.List;


/*
 * DataBase Management with SQLite
 * */
public class Helper extends SQLiteOpenHelper {

    public static final String dataBaseName = "NOTIFIRE.DB";
    public static final String studentsTableName = "STUDENTS";
    public static final String adminsTableName = "ADMINS";

    public Helper(Context context) {
        super(context, dataBaseName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ studentsTableName+ " (ID INTERGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARK INTEGER, MAKEDATE DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+studentsTableName);
        onCreate(db);
    }
}

