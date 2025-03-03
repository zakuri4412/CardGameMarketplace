package com.example.cardgamemarket.data.database.Card;

import androidx.lifecycle.LiveData;
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

    @Query("Select * from cards where name= :name")
    List<CardEntity> getCardByName(String name);

    @Query("SELECT * FROM cards")
    List<CardEntity> getListCard();

    @Query("SELECT * FROM cards ORDER BY price DESC LIMIT 5")
    LiveData<List<CardEntity>> getTop5Cards();

    @Query("SELECT * FROM cards WHERE name LIKE :query")
    LiveData<List<CardEntity>> searchCards(String query);
}
