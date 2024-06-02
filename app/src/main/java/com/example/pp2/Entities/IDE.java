package com.example.pp2.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ide")
public class IDE {
    @PrimaryKey(autoGenerate = true)
    private int ideId;

    @ColumnInfo(name = "ide_name")
    private final String ide_name;

    public IDE(String ide_name) {
        this.ide_name = ide_name;
    }

    public void setIdeId(int ideId) {
        this.ideId = ideId;
    }

    public int getIdeId() {
        return ideId;
    }

    public String getIde_name() {
        return ide_name;
    }
}
