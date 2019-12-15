package com.example.oumaima.notifire;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import com.example.oumaima.notifire.models.Mark;
import com.example.oumaima.notifire.models.User;
import com.google.gson.Gson;

public class StudentDetails extends AppCompatActivity {

    public static final String PERSISTENCE_DATA = "persistence";

    TextView IdTextView, nameTextView, surnameTextView, markTextView, adminIdTextView;
    Button publishMark, back;
    Mark mark;

    User currentUser;
    SQLiteDataBase notifireDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        User extraObject = (User)getIntent().getSerializableExtra("studentData");
        Log.d("StudntDetail", "StudentDetails : " + extraObject.getId());

        notifireDatabase = new SQLiteDataBase(this);
        IdTextView = (TextView)findViewById(R.id.studentPersonalID);
        nameTextView = (TextView)findViewById(R.id.name);
        surnameTextView = (TextView)findViewById(R.id.surname);
        markTextView = (TextView)findViewById(R.id.mark);
        adminIdTextView = (TextView)findViewById(R.id.adminMarkId);
        publishMark = (Button)findViewById(R.id.submitMark);
        back = (Button)findViewById(R.id.clear);

        Log.d("StudntDetail", "Mark Id : " + extraObject.getMarkId());
        if (extraObject.getMarkId() == 0) {
            markTextView.setHint("undefined");
            adminIdTextView.setText("undefined");
        }
        else {
            Mark mark = getStudentMark(extraObject.getMarkId());
            markTextView.setText(mark.getStudentMark());
            adminIdTextView.setText(mark.getAdminId());
        }

        IdTextView.setText(Integer.toString(extraObject.getId()));
        nameTextView.setText(extraObject.getFirstName());
        surnameTextView.setText(extraObject.getLastName());

        publishMark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loadPersistenceData();
                long insertMarkId = notifireDatabase.insetNewMark(markTextView.getText().toString().trim(), currentUser.getId());
                boolean resUpdate = notifireDatabase.updateStudentMarkId(IdTextView.getText().toString().trim(), String.valueOf(insertMarkId));
                Toast.makeText(StudentDetails.this, insertMarkId + " " + resUpdate,Toast.LENGTH_LONG).show();
                load();
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                load();
            }
        });
    }

    private Mark getStudentMark(int markId) {
        Cursor results = notifireDatabase.findMarkById(markId);
        results.moveToFirst();
        while ( !results.isAfterLast() ){
            mark = new Mark();
            mark.setId(results.getInt(results.getColumnIndex("ID")));
            mark.setStudentMark(results.getString(results.getColumnIndex("STUDENTMARK")));
            mark.setMarkDate( new Date(results.getLong(results.getColumnIndex("MARKDATE"))*1000));
            mark.setAdminId(results.getInt(results.getColumnIndex("ADMINID")));
            results.moveToNext();
        }
        return mark;
    }

    public void loadPersistenceData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PERSISTENCE_DATA, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("currentUser", "");
        currentUser = gson.fromJson(json, User.class);
    }

    public void load(){
        Intent intent = new Intent(StudentDetails.this, AllStudent.class);
        startActivity(intent);
    }
}
