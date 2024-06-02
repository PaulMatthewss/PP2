package com.example.pp2.Entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"user_id", "sid"})
public class UserSubjectCrossRef {
    public long user_id;
    public long sid;
}
