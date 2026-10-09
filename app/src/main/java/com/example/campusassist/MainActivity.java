package com.example.campusassist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(view -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
        });
    }
}