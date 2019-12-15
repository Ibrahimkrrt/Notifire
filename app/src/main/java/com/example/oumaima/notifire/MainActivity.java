package com.example.oumaima.notifire;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.oumaima.notifire.models.User;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements AdminRegistryInfosDialog.IAdminDialogListener,StudentRegistryInfosDialog.IStudentDialogListener {

    public static final String PERSISTENCE_DATA = "persistence";
    public static final String PERSISTENCE_USER_DATA = "currentUser";

    TextView signInText, signUpText;
    EditText sFistName, sLastName,emailInput,passwordInput;
    Switch ifAdmin;
    Button clear, create, selectAll,action;
    boolean signIn;
    User user;

    SQLiteDataBase notifireDatabase;

    @Override
    public void getFields(String firstName, String lastName) {
        notifireDatabase.insetNewAdmin(emailInput.getText().toString().trim(),
                passwordInput.getText().toString().trim(),
                firstName,
                lastName);
        user.setEmail(emailInput.getText().toString().trim());
        user.setPassword(passwordInput.getText().toString().trim());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        openSelectionActivity();
    }

    @Override
    public void getFields(String firstName, String lastName, String school, String level, String studentClass, String group) {
        notifireDatabase.insetNewStudent(emailInput.getText().toString().trim(),
                passwordInput.getText().toString().trim(),
                firstName,
                lastName);
        user.setEmail(emailInput.getText().toString().trim());
        user.setPassword(passwordInput.getText().toString().trim());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSchool(school);
        user.setLevel(level);
        user.setStudentClass(studentClass);
        user.setGroup(group);
        openSelectionActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notifireDatabase = new SQLiteDataBase(this);
        //notifireDatabase.Recreate();

        signInText = (TextView)findViewById(R.id.signinText);
        signUpText = (TextView)findViewById(R.id.signupText);
        emailInput = (EditText)findViewById(R.id.emailInput);
        passwordInput = (EditText)findViewById(R.id.passwordInput);
        sFistName = (EditText)findViewById(R.id.sFirstName);
        sLastName = (EditText)findViewById(R.id.sLastName);
        ifAdmin = (Switch)findViewById(R.id.admin);
        create = (Button)findViewById(R.id.create);
        clear = (Button)findViewById(R.id.clear);
        selectAll = (Button)findViewById(R.id.selectAll);
        action = (Button)findViewById(R.id.actionButton);

        signIn = true;

        user = new User();

        signUpText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signIn = false;
                signInText.setTextColor(Color.parseColor("#AAAAAA"));
                signUpText.setTextColor(Color.parseColor("#000000"));
                action.setText("Sign up");
            }
        });

        signInText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signIn = true;
                signUpText.setTextColor(Color.parseColor("#AAAAAA"));
                signInText.setTextColor(Color.parseColor("#000000"));
                action.setText("Sign in");
            }
        });

        action.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (signIn){
                    Boolean a = notifireDatabase.login(emailInput.getText().toString().trim(),passwordInput.getText().toString().trim());
                    // Log.d("MainActivity : "," " + a);
                    openSelectionActivity();
                }
                else{
                    if(ifAdmin.isChecked()){
                        AdminRegistryInfosDialog registeAdminRegistryInfosDialog = new AdminRegistryInfosDialog();
                        registeAdminRegistryInfosDialog.show(getSupportFragmentManager(),"Informations");
                    }
                    else {
                        StudentRegistryInfosDialog registeStudentRegistryInfosDialog = new StudentRegistryInfosDialog();
                        registeStudentRegistryInfosDialog.show(getSupportFragmentManager(),"Informations");
                    }
                }
            }
        });
    }

    public void openSelectionActivity(){
        Intent intent = new Intent(MainActivity.this, AllStudent.class);
        //intent.putExtra(string,value);
        savePersistenceData();
        startActivity(intent);
    }

    public void savePersistenceData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PERSISTENCE_DATA, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(PERSISTENCE_USER_DATA,json);
        editor.commit();
    }
}
