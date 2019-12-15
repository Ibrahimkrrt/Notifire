package com.example.oumaima.notifire;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.oumaima.notifire.models.Mark;
import com.example.oumaima.notifire.models.User;

import java.util.ArrayList;
import java.util.Date;

public class StudentListAdapter extends ArrayAdapter<User> {
    private Context context;
    int resoucesId;
    Mark mark;

    public StudentListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
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
        int markId = getItem(position).getMarkId();

        User selectedUser = new User();
        selectedUser.setId(id);
        selectedUser.setFirstName(name);
        selectedUser.setLastName(surname);
        selectedUser.setMarkId(markId);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resoucesId,parent,false);

        TextView updatedBy = (TextView)convertView.findViewById(R.id.updatedBy);
        TextView mark = (TextView)convertView.findViewById(R.id.studentMark);
        TextView studentFirstName = (TextView)convertView.findViewById(R.id.studentFistName);
        TextView studentLastName = (TextView)convertView.findViewById(R.id.studentLastName);
        if (markId == 0) {
            mark.setText("undefined");
        }
        else {
            Mark studentMark = getStudentMark(markId);
            mark.setText(studentMark.getStudentMark());
            updatedBy.setText(studentMark.getMarkDate().toString());
        }
        studentFirstName.setText(name);
        studentLastName.setText(surname);

        return convertView;
    }

    private Mark getStudentMark(int markId) {
        SQLiteDataBase notifireDatabase = new SQLiteDataBase(context);
        Cursor results = notifireDatabase.findMarkById(markId);
        results.moveToFirst();
        while ( !results.isAfterLast() ){
            mark = new Mark();
            mark.setId(results.getInt(results.getColumnIndex("ID")));
            mark.setStudentMark(results.getString(results.getColumnIndex("STUDENTMARK")));
            mark.setMarkDate( new Date(results.getLong(results.getColumnIndex("MARKDATE"))*1000));
            mark.setAdminId(results.getInt(results.getColumnIndex("ADMINID")));
            results.moveToNext();
        }
        return mark;
    }
}
