package com.example.cardgamemarket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.LiveData;

import com.bumptech.glide.Glide;
import com.example.cardgamemarket.broadcast.PurchaseReceiver;
import com.example.cardgamemarket.data.database.Card.CardEntity;
import com.example.cardgamemarket.data.database.User.User;
import com.example.cardgamemarket.data.database.User.UserDAO;
import com.example.cardgamemarket.data.provider.ConnectionHelper;

import java.util.List;

public class DetailActivity  extends BaseActivity {
    private UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityLayout(R.layout.activity_detail);

        String cardName = getIntent().getStringExtra("CardName");
        double price = getIntent().getDoubleExtra("CardPrice",0.0);
        String cardImageUrl = getIntent().getStringExtra("BigImageUrl");
        int sellerId = getIntent().getIntExtra("UserId",0);
        ConnectionHelper db = ConnectionHelper.getInstance(this);
        userDAO = db.traderDAO();
        User getUser = userDAO.getTraderById(sellerId);

        TextView nameTxt = findViewById(R.id.cardName_txt);
        TextView priceTxt = findViewById(R.id.sellerPrice_txt);
        TextView sellerName = findViewById(R.id.sellerName_txt);
        ImageView image = findViewById(R.id.bigImageView);

        Glide.with(this)
                .load(cardImageUrl)
                .into(image);
        nameTxt.setText(cardName);
        priceTxt.setText("Price: $"+price);
        sellerName.setText(getUser.name);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DetailActivity", "Press button");
                Intent intent = new Intent(DetailActivity.this, PurchaseReceiver.class);
                intent.setAction("com.example.PURCHASE_SUCCESS");
                intent.putExtra("item_name", cardName);
                intent.putExtra("seller_name", getUser.name);
                sendBroadcast(intent);
                Log.d("DetailActivity", "Broadcast send");
            }
        });
    }

}