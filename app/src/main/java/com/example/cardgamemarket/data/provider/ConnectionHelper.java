package com.example.cardgamemarket.data.provider;

import android.os.StrictMode;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.room.Database;

import com.example.cardgamemarket.data.database.Card.CardDAO;
import com.example.cardgamemarket.data.database.Card.CardEntity;
import com.example.cardgamemarket.data.database.User.User;
import com.example.cardgamemarket.data.database.User.UserDAO;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.sql.Connection;

@Database(entities = {User.class, CardEntity.class}, version = 1, exportSchema = false)
public abstract class ConnectionHelper extends RoomDatabase {
    private static volatile ConnectionHelper INSTANCE;

    public abstract UserDAO traderDAO();
    public abstract CardDAO cardDAO();

    public static ConnectionHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ConnectionHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    ConnectionHelper.class, "app_database"
                            )
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}



