package com.example.pp2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "groups")
public class Group {
    @PrimaryKey(autoGenerate = true)
    private int gid;

    @ColumnInfo(name = "group_name")
    private String group_name;

    @ColumnInfo(name = "group_year")
    private int group_year;

    @ColumnInfo(name = "subject_name")
    private String subject_name;

    public Group(String group_name, int group_year){
        this.group_name = group_name;
        this.group_year = group_year;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getGid() {
        return gid;
    }

    public String getGroup_name() {
        return group_name;
    }

    public int getGroup_year() {
        return group_year;
    }

    public String getSubject_name() {
        return subject_name;
    }
}
