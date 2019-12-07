package com.example.oumaima.notifire.models;

import java.util.Date;

public class Mark {
    private int id; // STUDENTMARK TEXT, MARKDATE DATE, ADMINID INTEGER
    private String studentMark;
    private Date markDate;
    private int adminId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(String studentMark) {
        this.studentMark = studentMark;
    }

    public Date getMarkDate() { return markDate; }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }

    public int getAdminId() { return adminId; }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public Mark() {
    }
}
