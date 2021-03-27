package com.Rai.studycenter.models;

public class Notesmodel {
    String Name,desc;
    int id;
    public Notesmodel(){};
    public Notesmodel(String name, String desc, int id) {
        Name = name;
        this.desc = desc;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
