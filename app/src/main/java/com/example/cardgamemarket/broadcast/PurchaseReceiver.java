package com.example.cardgamemarket.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PurchaseReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("PurchaseReceiver", "Broadcast received!");
        String itemName = intent.getStringExtra("item_name");
        String sellerName = intent.getStringExtra("seller_name");
        if(itemName != null || sellerName != null){
            Log.d("PurchaseReceiver", "Buy success!");
            Toast.makeText(context, "Buy Sucess: " + itemName + " from "+ sellerName, Toast.LENGTH_SHORT).show();

        }else{
            Log.d("PurchaseReceiver", "No data was sent");
        }
    }
}
