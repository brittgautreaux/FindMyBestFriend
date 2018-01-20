package com.example.britt_000.dogfinder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.britt_000.dogfinder.R;

public class UserAreaActivity extends AppCompatActivity {
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        textViewName = (TextView) findViewById(R.id.text1);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome " + nameFromIntent);
    }
}
