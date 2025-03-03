package com.example.cardgamemarket.data.database.User;

import androidx.room.*;

import java.util.List;

@Dao
public interface UserDAO {
        @Insert
        long insertTrader(User user);

        @Delete
        void deleteTrader(User user);

        @Query("SELECT * FROM trader")
        List<User> getAllTraders();

        @Query("SELECT * FROM trader WHERE Id = :id LIMIT 1")
        User getTraderById(int id);

        @Query("DELETE FROM trader WHERE Id = :userId")
        void deleteTraderById(int userId);
}
