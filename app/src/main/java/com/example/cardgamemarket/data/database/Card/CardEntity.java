package com.example.cardgamemarket.data.database.Card;

import androidx.room.*;

import com.example.cardgamemarket.data.database.User.User;

@Entity(
        tableName = "cards",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "Id",
                childColumns = "seller_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index(value = "seller_id")}
)
public class CardEntity {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "price")
    public double price;
    @ColumnInfo(name = "card_api_id")
    public String cardAPIId;
    @ColumnInfo(name = "seller_id")
    public int sellerId;

    public CardEntity(String name, double price, int sellerId, String cardAPIId){
        this.name = name;
        this.price = price;
        this.sellerId = sellerId;
        this.cardAPIId = cardAPIId;
    }
}
