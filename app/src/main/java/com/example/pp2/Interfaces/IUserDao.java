package com.example.pp2.Interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.pp2.Entities.UserWithSubjects;

import java.util.List;

public interface IUserDao {
    @Transaction
    @Query("SELECT * FROM users")
    LiveData<List<UserWithSubjects>> getUserWithSubjects();
}
