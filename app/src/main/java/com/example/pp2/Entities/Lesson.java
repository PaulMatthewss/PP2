package com.example.pp2.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lessons")
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    private int les_id;
    private final Integer lesson_num;
    private final String lesson_type;
    private final String lesson_date;
    private final String subject;
    private final String group;
    private final Integer student_num;
    private final Integer grade;
    private final boolean is_accepted;

    public Lesson(Integer lesson_num, String lesson_type, String lesson_date, String subject, String group, Integer student_num, Integer grade, boolean is_accepted) {
        this.lesson_num = lesson_num;
        this.lesson_type = lesson_type;
        this.lesson_date = lesson_date;
        this.subject = subject;
        this.group = group;
        this.student_num = student_num;
        this.grade = grade;
        this.is_accepted = is_accepted;
    }

    public void setLes_id(int les_id) {
        this.les_id = les_id;
    }

    public int getLes_id() {
        return les_id;
    }

    public Integer getLesson_num() {
        return lesson_num;
    }
    public String getLesson_type() {
        return lesson_type;
    }

    public String getLesson_date() {
        return lesson_date;
    }

    public String getSubject() {
        return subject;
    }

    public String getGroup() {
        return group;
    }

    public Integer getStudent_num() {
        return student_num;
    }

    public Integer getGrade() {
        return grade;
    }

    public boolean isIs_accepted() {
        return is_accepted;
    }
}
