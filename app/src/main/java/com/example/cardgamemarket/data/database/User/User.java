package com.example.cardgamemarket.data.database.User;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trader")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    @ColumnInfo(name = "name")
    public String name;

    public User(String name) {
        this.name = name;
    }
}
