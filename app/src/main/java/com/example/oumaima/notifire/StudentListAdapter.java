package com.example.oumaima.notifire;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.oumaima.notifire.models.Student;

import java.util.ArrayList;

public class StudentListAdapter extends ArrayAdapter<Student> {
    private Context context;
    int resoucesId;

    public StudentListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        resoucesId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        String name = getItem(position).getFirstName();
        String surname = getItem(position).getLastName();

        Student selectedStudent = new Student();
        selectedStudent.setId(id);
        selectedStudent.setFirstName(name);
        selectedStudent.setLastName(surname);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resoucesId,parent,false);

        TextView studentId = (TextView)convertView.findViewById(R.id.studentId);
        TextView studentFirstName = (TextView)convertView.findViewById(R.id.studentFistName);
        TextView studentLastName = (TextView)convertView.findViewById(R.id.studentLastName);

        studentId.setText(Integer.toString(id));
        studentFirstName.setText(name);
        studentLastName.setText(surname);

        return convertView;
    }
}
