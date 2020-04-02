package com.example.foodvilage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_logIn();
        user_Register();
    }
    private void user_logIn() {
        findViewById(R.id.btn_loginId).setOnClickListener((View view)->{
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        });
    }
    private void user_Register() {
        findViewById(R.id.btn_signupId).setOnClickListener((View view)->{
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        });
    }
}
