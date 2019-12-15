package com.example.oumaima.notifire;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.oumaima.notifire.models.User;

import java.util.ArrayList;

public class AllStudent extends AppCompatActivity {

    ListView studentList;
    User user;
    ArrayList<User> userArrayList;
    ArrayList<User> sList;

    SQLiteDataBase notifireDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_student);

        notifireDatabase = new SQLiteDataBase(this);

        studentList = (ListView)findViewById(R.id.studentList);
        userArrayList = new ArrayList<>();
        showStudentData();

        studentList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final User clickedUser = (User)parent.getItemAtPosition(position);
                Log.d("itemClick","you clicked on "+ clickedUser.getId());
                Intent intent = new Intent(AllStudent.this, StudentDetails.class);
                intent.putExtra("studentData", clickedUser);
                startActivity(intent);
            }
        });

    }

    private void showStudentData() {
        Cursor results = notifireDatabase.selectAllStudent();
        results.moveToFirst();
        while ( !results.isAfterLast() ){
            user = new User();
            user.setId(results.getInt(results.getColumnIndex("ID")));
            user.setFirstName(results.getString(results.getColumnIndex("NAME")));
            user.setLastName(results.getString(results.getColumnIndex("SURNAME")));
            user.setMarkId(results.getInt(results.getColumnIndex("MARKID")));
            userArrayList.add(user);
            StudentListAdapter adapter = new StudentListAdapter(this, R.layout.student_info, userArrayList);
            studentList.setAdapter(adapter);
            results.moveToNext();
        }
    }

}
