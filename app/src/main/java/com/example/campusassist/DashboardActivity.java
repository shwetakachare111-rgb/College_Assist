package com.example.campusassist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button reportButton;
    Button myComplaintsButton;
    Button noticesButton;
    Button emergencyButton;
    Button reminderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        reportButton = findViewById(R.id.reportButton);
        myComplaintsButton = findViewById(R.id.myComplaintsButton);
        noticesButton = findViewById(R.id.noticesButton);
        emergencyButton = findViewById(R.id.emergencyButton);
        reminderButton = findViewById(R.id.reminderButton);

        reportButton.setOnClickListener(view -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    ReportProblemActivity.class
            );

            startActivity(intent);
        });

        myComplaintsButton.setOnClickListener(view -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    MyComplaintsActivity.class
            );

            startActivity(intent);
        });

        noticesButton.setOnClickListener(view -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    NoticesActivity.class
            );

            startActivity(intent);
        });

        emergencyButton.setOnClickListener(view -> {

            Intent intent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:112")
            );

            startActivity(intent);
        });

        reminderButton.setOnClickListener(view -> {

            Intent intent = new Intent(
                    DashboardActivity.this,
                    ReminderReceiver.class
            );

            sendBroadcast(intent);
        });
    }
}