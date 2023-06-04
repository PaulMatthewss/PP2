package com.example.pp2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IStudentDao {
    @Query("SELECT * FROM students")
    LiveData<List<Student>> getAllStudents();
    @Insert
    void insertStudent(Student student);
    @Update
    void updateStudent(Student student);
    @Delete
    void deleteStudent(Student student);
}
