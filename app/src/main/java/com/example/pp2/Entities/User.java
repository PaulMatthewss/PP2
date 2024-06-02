package com.example.pp2.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int user_id;
    @ColumnInfo(name = "name")
    private final String name;
    @ColumnInfo(name = "family_name")
    private final String family_name;
    @ColumnInfo(name = "patronymic")
    private final String patronymic;
    @ColumnInfo(name = "e_mail")
    private final String e_mail;
    @ColumnInfo(name = "password")
    private final String password;


    public User(String name, String family_name, String patronymic, String e_mail, String password) {
        this.name = name;
        this.family_name = family_name;
        this.patronymic = patronymic;
        this.e_mail = e_mail;
        this.password = password;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getE_mail() {
        return e_mail;
    }

    public String getPassword() {
        return password;
    }
}
