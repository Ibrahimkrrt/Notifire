package com.example.oumaima.notifire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.oumaima.notifire.models.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class allStudent extends AppCompatActivity {

    ListView studentList;
    DatabaseReference fireBaseReff;
    Student student;
    ArrayList<Student> studentArrayList;
    ArrayList<Student> sList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_student);

        studentList = (ListView)findViewById(R.id.studentList);
        student = new Student();
        studentArrayList = new ArrayList<>();

        fireBaseReff = FirebaseDatabase.getInstance().getReference().child("Student");
        fireBaseReff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds: dataSnapshot.getChildren()){
            student = ds.getValue(Student.class);

            studentArrayList.add(student);
            StudentListAdapter adapter = new StudentListAdapter(this, R.layout.student_info,studentArrayList);
            studentList.setAdapter(adapter);
        }
    }

}
