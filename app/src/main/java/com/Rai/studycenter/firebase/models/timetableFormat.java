package com.Rai.studycenter.firebase.models;

public class timetableFormat {
    String Day,date,time,subject;
    public timetableFormat(){};

    public timetableFormat(String day, String date, String time, String subject) {
        Day = day;
        this.date = date;
        this.time = time;
        this.subject = subject;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
