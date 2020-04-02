package com.example.foodvilage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout userLoginEmail, userLoginPassword;
    private EditText edt_email, edt_password;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLoginEmail = findViewById(R.id.user_login_email_WrapperId);
        userLoginPassword = findViewById(R.id.user_login_password_WrapperId);

        edt_email = findViewById(R.id.user_login_emailId);
        edt_password = findViewById(R.id.user_login_passwordId);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_loginId).setOnClickListener((View view)->{
            userLogin();
        });

        findViewById(R.id.tv_signupId).setOnClickListener((View view)->{
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
        });
    }

    private void userLogin() {

        String email = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();

        if (email.isEmpty()){
            userLoginEmail.setError("Enter Email");
            userLoginEmail.requestFocus();
            return;
        }if (password.isEmpty()){
            userLoginPassword.setError("Enter password");
            userLoginPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(),ShopActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
