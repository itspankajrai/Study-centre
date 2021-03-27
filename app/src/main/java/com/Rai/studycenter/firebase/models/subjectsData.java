package com.Rai.studycenter.firebase.models;

public class subjectsData {
    String subjectName,Date,time,day;
    public subjectsData(){}
    public subjectsData(String subjectName, String date, String time, String day) {
        this.subjectName = subjectName;
        Date = date;
        this.time = time;
        this.day = day;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
