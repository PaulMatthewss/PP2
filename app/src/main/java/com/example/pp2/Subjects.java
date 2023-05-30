package com.example.pp2;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

@Entity(tableName = "subjects")
public class Subjects {
    @PrimaryKey(autoGenerate = true)
    public int sid;

    @ColumnInfo(name = "subject_name")
    public String subject_name;

    @ColumnInfo(name = "subject_lang")
    public String subject_lang;

    @ColumnInfo(name = "subject_ide")
    public String subject_ide;
}
