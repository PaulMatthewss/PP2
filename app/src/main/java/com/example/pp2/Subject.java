package com.example.pp2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subjects")
public class Subject {
    @PrimaryKey(autoGenerate = true)
    private int sid;

    @ColumnInfo(name = "subject_name")
    private String name;

    @ColumnInfo(name = "subject_lang")
    private String lang;

    @ColumnInfo(name = "subject_ide")
    private String ide;

    public Subject(String name, String lang, String ide) {
        this.name = name;
        this.lang = lang;
        this.ide = ide;
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

    public String getLang() {
        return lang;
    }

    public String getIde() {
        return ide;
    }
}
