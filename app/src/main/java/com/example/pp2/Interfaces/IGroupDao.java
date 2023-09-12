package com.example.pp2.Interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pp2.Entities.Group;

import java.util.List;

@Dao
public interface IGroupDao {
    @Query("SELECT * FROM groups")
    LiveData<List<Group>> getAllGroups();
    @Insert
    void insertGroup(Group group);
    @Update
    void updateGroup(Group group);
    @Delete
    void deleteGroup(Group group);
}
