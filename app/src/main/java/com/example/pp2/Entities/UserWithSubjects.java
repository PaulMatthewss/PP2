package com.example.pp2.Entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class UserWithSubjects {
    @Embedded public User user;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "sid",
            associateBy = @Junction(UserSubjectCrossRef.class)

    )
    public List<Subject> subjects;
}
