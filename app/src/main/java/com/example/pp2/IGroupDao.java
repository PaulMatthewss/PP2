package com.example.pp2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IGroupDao {
    @Query("SELECT groups.group_name AS groupName, groups.group_year AS groupYear " +
            "FROM subjects, groups " +
            "WHERE subjects.subject_name = groups.subject_name")
    LiveData<List<SubjectGroup>> getGroups();
    @Query("SELECT * FROM groups")
    LiveData<List<Group>> getAllGroups();
    @Insert
    void insertGroup(Group group);
    @Update
    void updateGroup(Group group);
    @Delete
    void deleteGroup(Group group);
}
