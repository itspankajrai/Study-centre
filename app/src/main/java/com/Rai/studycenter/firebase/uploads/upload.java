package com.Rai.studycenter.firebase.uploads;

public  class upload{
    String semName,subjectName,subjectUrl,UniqueID;

    public upload() {

    }

    public upload(String semName, String subjectName, String subjectUrl,String UniqueID){
        this.semName=semName;
        this.subjectName=subjectName;
        this.subjectUrl=subjectUrl;
        this.UniqueID=UniqueID;
        
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectUrl() {
        return subjectUrl;
    }

    public void setSubjectUrl(String subjectUrl) {
        this.subjectUrl = subjectUrl;
    }

    public String getUniqueID() {
        return UniqueID;
    }

    public void setUniqueID(String uniqueID) {
        UniqueID = uniqueID;
    }
}