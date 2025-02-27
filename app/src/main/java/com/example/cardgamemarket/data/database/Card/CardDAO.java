package com.example.cardgamemarket.data.database.Card;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CardDAO {
    @Insert
    void InsertCard(CardEntity card);

    @Update
    void UpdateCard(CardEntity card);

    @Delete
    void DeleteCard(CardEntity card);

    @Query("SELECT * FROM cards WHERE seller_id = :sellerId")
    List<CardEntity> getCardBySeller(int sellerId);

    @Query("SELECT * FROM cards")
    List<CardEntity> getListCard();
}
