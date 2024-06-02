package com.example.pp2.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subjects")
public class Subject {
    @PrimaryKey(autoGenerate = true)
    private int sid;

    @ColumnInfo(name = "subject_name")
    private final String name;

    @ColumnInfo(name = "langId")
    private final String langId;

    @ColumnInfo(name = "ideId")
    private final String ideId;

    public Subject(String name, String langId, String ideId) {
        this.name = name;
        this.langId = langId;
        this.ideId = ideId;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public String getLangId() {
        return langId;
    }

    public String getIdeId() {
        return ideId;
    }
}
