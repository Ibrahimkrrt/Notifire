package com.example.oumaima.notifire;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.oumaima.notifire.models.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllStudent extends AppCompatActivity {

    ListView studentList;
    // DatabaseReference fireBaseReff;
    Student student;
    ArrayList<Student> studentArrayList;
    ArrayList<Student> sList;

    SQLiteDataBase notifireDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_student);

        notifireDatabase = new SQLiteDataBase(this);

        studentList = (ListView)findViewById(R.id.studentList);
        studentArrayList = new ArrayList<>();
        showStudentData();

        studentList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Student clickedStudent  = (Student)parent.getItemAtPosition(position);
                Log.d("itemClick","you clicked on "+clickedStudent.getId());
                Intent intent = new Intent(AllStudent.this, StudentDetails.class);
                intent.putExtra("studentData",clickedStudent);
                startActivity(intent);
            }
        });

    }

    private void showStudentData() {
        Cursor results = notifireDatabase.selectAllStudent();
        results.moveToFirst();
        while ( !results.isAfterLast() ){
            student = new Student();
            student.setId(results.getInt(results.getColumnIndex("ID")));
            student.setFirstName(results.getString(results.getColumnIndex("NAME")));
            student.setLastName(results.getString(results.getColumnIndex("SURNAME")));
            studentArrayList.add(student);
            StudentListAdapter adapter = new StudentListAdapter(this, R.layout.student_info,studentArrayList);
            studentList.setAdapter(adapter);
            results.moveToNext();
        }
    }

}
