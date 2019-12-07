package com.example.oumaima.notifire;


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

public class StudentDetails extends AppCompatActivity {

    TextView IdTextView;
    TextView nameTextView;
    TextView surnameTextView;
    TextView markTextView;
    Button publishMark;
    Mark mark;

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
        publishMark = (Button)findViewById(R.id.submitMark);

        Log.d("StudntDetail", "Mark Id : " + extraObject.getMarkId());
        if (extraObject.getMarkId() == 0) {
            markTextView.setHint( "undefined" );
        }
        else {
            String publishedMark = getStudentMark(extraObject.getMarkId());
            markTextView.setText(publishedMark);
        }

        IdTextView.setText( Integer.toString(extraObject.getId()) );
        nameTextView.setText(extraObject.getFirstName());
        surnameTextView.setText(extraObject.getLastName());

        publishMark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                long insertMarkId = notifireDatabase.insetNewMark(markTextView.getText().toString().trim(),94);
                boolean resUpdate = notifireDatabase.updateStudentMarkId(IdTextView.getText().toString().trim(), String.valueOf(insertMarkId));
                Toast.makeText(StudentDetails.this, insertMarkId + " " + resUpdate,Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getStudentMark(int markId) {
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
        return mark.getStudentMark();
    }

}
