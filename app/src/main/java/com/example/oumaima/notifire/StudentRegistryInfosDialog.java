package com.example.oumaima.notifire;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class StudentRegistryInfosDialog extends AppCompatDialogFragment {

    private EditText firstNameText, lastNameText, schoolText, levelText, studentClassText, groupText;
    IStudentDialogListener studentDialogListener;

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.student_dialoge_registry,null);
        firstNameText = (EditText)view.findViewById(R.id.studentFirstNameDialog);
        lastNameText = (EditText)view.findViewById(R.id.studentLastNameDialog);
        schoolText = (EditText)view.findViewById(R.id.studentSchoolDialog);
        levelText = (EditText)view.findViewById(R.id.studentLevelDialog);
        studentClassText = (EditText)view.findViewById(R.id.studentClassDialog);
        groupText = (EditText)view.findViewById(R.id.studentGroupDialog);

        builder
                .setView(view)
                .setTitle("sign up information")
                .setNegativeButton("Abort", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    studentDialogListener.getFields(firstNameText.getText().toString().trim(),
                            lastNameText.getText().toString().trim(),
                            schoolText.getText().toString().trim(),
                            levelText.getText().toString().trim(),
                            studentClassText.getText().toString().trim(),
                            groupText.getText().toString().trim());
                    }
                });
        return builder.create();
    }



    public interface IStudentDialogListener{
        void getFields(String firstName, String lastName, String school, String level, String studentClass, String group);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            studentDialogListener = (IStudentDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException ("Interface data not implemented");
        }
    }
}
