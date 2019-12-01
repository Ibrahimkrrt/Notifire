package com.example.oumaima.notifire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oumaima.notifire.models.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText sFistName, sLastName, sMark;
    Button clear, create, selectAll;
    DatabaseReference fireBaseReff;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sFistName = (EditText)findViewById(R.id.sFirstName);
        sLastName = (EditText)findViewById(R.id.sLastName);
        sMark = (EditText)findViewById(R.id.sMark);
        create = (Button)findViewById(R.id.create);
        clear = (Button)findViewById(R.id.clear);
        selectAll = (Button)findViewById(R.id.selectAll);
        student = new Student();

        fireBaseReff = FirebaseDatabase.getInstance().getReference().child("Student");
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Double mark = Double.parseDouble(sMark.getText().toString().trim());
                Date markDate = new Date();

                student.setFirstName(sFistName.getText().toString().trim());
                student.setLastName(sLastName.getText().toString().trim());
                student.setMark(mark);
                student.setMarkDate(markDate);
                fireBaseReff.push().setValue(student);
                Toast.makeText(MainActivity.this, "Data inserted",Toast.LENGTH_LONG).show();
            }
        });

        /*selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectionActivity();
            }
        });*/
    }
    public void openSelectionActivity(View view){
        Intent intent = new Intent(this, allStudent.class);
        //intent.putExtra(string,value);
        startActivity(intent);
    }
}
