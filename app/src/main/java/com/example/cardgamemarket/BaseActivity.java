package com.example.cardgamemarket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BaseActivity extends AppCompatActivity {
    protected TextView headerTextView;
    protected ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        headerTextView = findViewById(R.id.header_text);
        mainLayout = findViewById(R.id.main);
    }


    // Nạp layout riêng của Activity con vào BaseActivity
    protected void setActivityLayout(@LayoutRes int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, mainLayout, true);
    }
}