package com.example.pp2.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "groups")
public class Group {
    @PrimaryKey(autoGenerate = true)
    private int gid;

    @ColumnInfo(name = "group_name")
    private final String group_name;

    public Group(String group_name){
        this.group_name = group_name;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getGid() {
        return gid;
    }

    public String getGroup_name() {
        return group_name;
    }
}
