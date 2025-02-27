package com.example.cardgamemarket.data.database.Card;

import androidx.room.*;

@Entity(tableName = "cards")
public class CardEntity {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "price")
    public double price;
    @ColumnInfo(name = "seller_id")
    public int sellerId;

    public CardEntity(String name, double price, int sellerId){
        this.name = name;
        this.price = price;
        this.sellerId = sellerId;
    }
}
