package com.example.pp2.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int stud_id;

    @ColumnInfo(name = "student_fio")
    private final String fio;

    @ColumnInfo(name = "student_num")
    private final int stud_num;

    public Student(String fio, int stud_num) {
        this.fio = fio;
        this.stud_num = stud_num;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public int getStud_id() {
        return stud_id;
    }

    public String getFio() {
        return fio;
    }

    public int getStud_num() {
        return stud_num;
    }
}
