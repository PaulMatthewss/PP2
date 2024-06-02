package com.example.pp2.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "programming_languages")
public class P_lang {
    @PrimaryKey(autoGenerate = true)
    private int pid;

    @ColumnInfo(name = "lang_name")
    private final String lang_name;

    public P_lang(String langName) {
        this.lang_name = langName;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPid() {
        return pid;
    }

    public String getLang_name() {
        return lang_name;
    }
}
