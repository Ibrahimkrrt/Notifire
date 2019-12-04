package com.example.oumaima.notifire;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.ThreadLocalRandom;

import com.example.oumaima.notifire.models.Student;

public class StudentDetails extends AppCompatActivity {

    TextView id;
    TextView name;
    TextView surname;
    TextView mark;
    Button publishMark;

    SQLiteDataBase notifireDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        Student extraObject = (Student)getIntent().getSerializableExtra("studentData");

        notifireDatabase = new SQLiteDataBase(this);
        id = (TextView)findViewById(R.id.studentPersonalID);
        name = (TextView)findViewById(R.id.name);
        surname = (TextView)findViewById(R.id.surname);
        mark = (TextView)findViewById(R.id.mark);
        publishMark = (Button)findViewById(R.id.submitMark);

        Log.d("StudntDetail", "Mark Id is : " + extraObject.getMarkId());
        String publishedMark = "Undefined";
        if ( extraObject.getMarkId() != 0 ){
            publishedMark = getStudentMark(extraObject.getMarkId());
        }



        id.setText( Integer.toString(extraObject.getId()) );
        name.setText(extraObject.getFirstName());
        surname.setText(extraObject.getLastName());
        mark.setText( publishedMark );

        publishMark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                notifireDatabase.insetNewMark(mark.getText().toString().trim(),94);
                Toast.makeText(StudentDetails.this, "Data instered ",Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getStudentMark(int markId) {
        return notifireDatabase.findMarkById(markId);
    }

}
