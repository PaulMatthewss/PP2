package com.example.pp2.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "student_num")
    private final int stud_num;

    @ColumnInfo(name = "student_fio")
    private final String fio;

    public Student(int stud_num, String fio) {
        this.stud_num = stud_num;
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    public int getStud_num() {
        return stud_num;
    }
}
