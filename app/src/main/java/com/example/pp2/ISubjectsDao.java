package com.example.pp2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ISubjectsDao {
    @Query("SELECT * FROM subjects")
    List<Subjects> getAll();

    @Query("SELECT * FROM subjects WHERE sid IN (:subjectsIds)")
    List<Subjects> loadAllByIds(int[] subjectsIds);

    @Insert
    void insertAll(Subjects... subjects);

    @Delete
    void delete(Subjects subject);
}