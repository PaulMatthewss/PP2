package com.example.pp2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ISubjectsDao {
    @Query("SELECT * FROM subjects")
    LiveData<List<Subject>> getAll();

    @Insert
    void insertSubject(Subject subject);
    @Update
    void updateSubject(Subject subject);

    @Delete
    void deleteSubject(Subject subject);
}