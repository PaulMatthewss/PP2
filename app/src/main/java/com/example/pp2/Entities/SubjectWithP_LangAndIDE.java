package com.example.pp2.Entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SubjectWithP_LangAndIDE {
    @Embedded public Subject subject;
    @Relation(
            parentColumn = "pid",
            entityColumn = "langId"
    )
    public List<P_lang> p_langs;
    @Relation(
            parentColumn = "ideId",
            entityColumn = "ideId"
    )
    public List<IDE> ides;
}
