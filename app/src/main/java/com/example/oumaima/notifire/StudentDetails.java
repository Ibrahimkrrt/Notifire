package com.example.oumaima.notifire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oumaima.notifire.models.Student;

public class StudentDetails extends AppCompatActivity {

    TextView id;
    TextView name;
    TextView surname;
    TextView mark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        Student extraObject = (Student)getIntent().getSerializableExtra("studentData");

        id = (TextView)findViewById(R.id.studentPersonalID);
        name = (TextView)findViewById(R.id.name);
        surname = (TextView)findViewById(R.id.surname);
        mark = (TextView)findViewById(R.id.mark);

        id.setText( Integer.toString(extraObject.getId()) );
        name.setText(extraObject.getFirstName());
        surname.setText(extraObject.getLastName());
        mark.setText( Integer.toString(extraObject.getId()) );

    }

}
