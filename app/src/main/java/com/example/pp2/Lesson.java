package com.example.pp2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lessons")
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    private int les_id;
    private int lesson_num;
    private String lesson_date;
    private int subject;
    private String group;
    private int student_num;
    private String grade;

    public Lesson(int lesson_num, String lesson_date, int subject, String group, int student_num, String grade) {
        this.lesson_num = lesson_num;
        this.lesson_date = lesson_date;
        this.subject = subject;
        this.group = group;
        this.student_num = student_num;
        this.grade = grade;
    }

    public void setLes_id(int les_id) {
        this.les_id = les_id;
    }

    public int getLesson_num() {
        return lesson_num;
    }

    public String getLesson_date() {
        return lesson_date;
    }

    public int getSubject() {
        return subject;
    }

    public String getGroup() {
        return group;
    }

    public int getStudent_num() {
        return student_num;
    }

    public String getGrade() {
        return grade;
    }
}
