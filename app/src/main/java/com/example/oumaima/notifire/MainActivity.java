package com.example.oumaima.notifire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText sFistName, sLastName, sMark;
    Button clear, create, selectAll;
    //DatabaseReference fireBaseReff;
    //Student student;

    SQLiteDataBase notifireDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notifireDatabase = new SQLiteDataBase(this);

        sFistName = (EditText)findViewById(R.id.sFirstName);
        sLastName = (EditText)findViewById(R.id.sLastName);
        sMark = (EditText)findViewById(R.id.sMark);
        create = (Button)findViewById(R.id.create);
        clear = (Button)findViewById(R.id.clear);
        selectAll = (Button)findViewById(R.id.selectAll);


        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean resolver = notifireDatabase.insetNewStudent(sFistName.getText().toString().trim(),sLastName.getText().toString().trim());
                Log.d("database", "test : " +  resolver);
                if (resolver = true) Toast.makeText(MainActivity.this, "Data inserted ",Toast.LENGTH_LONG).show();
                else Toast.makeText(MainActivity.this, "Data rejected ",Toast.LENGTH_LONG).show();
            }
        });

    }
    public void openSelectionActivity(View view){
        Intent intent = new Intent(this, AllStudent.class);
        //intent.putExtra(string,value);
        startActivity(intent);
    }
}
