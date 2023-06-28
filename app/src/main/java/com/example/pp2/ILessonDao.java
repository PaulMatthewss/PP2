package com.example.pp2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ILessonDao {
    @Query("SELECT * FROM lessons")
    LiveData<List<Lesson>> getAllLessons();
    @Insert
    void insertLesson(Lesson lesson);
    @Update
    void updateLesson(Lesson lesson);
    @Delete
    void deleteLesson(Lesson lesson);
}
