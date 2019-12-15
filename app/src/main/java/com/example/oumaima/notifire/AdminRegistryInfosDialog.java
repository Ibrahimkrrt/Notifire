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

public class AdminRegistryInfosDialog extends AppCompatDialogFragment {

    private EditText firstNameText, lastNameText;
    private IAdminDialogListener adminDialogListener;


    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.admin_dialoge_registry,null);
        firstNameText = (EditText)view.findViewById(R.id.adminFirstNameDialog);
        lastNameText = (EditText)view.findViewById(R.id.adminLastNameDialog);

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
                        adminDialogListener.getFields(firstNameText.getText().toString().trim(),
                                lastNameText.getText().toString().trim());
                    }
                });
        return builder.create();
    }

    public interface IAdminDialogListener{
        void getFields(String firstName, String lastName);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            adminDialogListener = (IAdminDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException ("Interface data not implemented");
        }
    }
}
