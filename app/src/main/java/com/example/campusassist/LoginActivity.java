package com.example.campusassist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {

            String email = emailEditText
                    .getText()
                    .toString()
                    .trim();

            String password = passwordEditText
                    .getText()
                    .toString()
                    .trim();

            if (email.equals("student@gmail.com")
                    && password.equals("1234")) {

                Toast.makeText(
                        LoginActivity.this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent = new Intent(
                        LoginActivity.this,
                        DashboardActivity.class
                );

                startActivity(intent);

                finish();

            } else {

                Toast.makeText(
                        LoginActivity.this,
                        "Invalid Email or Password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}