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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText ufirst_name, ulast_name, uemail, upassword, uconfirm_password, ucontact;
    private TextInputLayout ufirst_name_wrapper, ulast_name_wrapper, uemail_wrapper, upassword_wrapper,
                            uconfirm_password_wrapper, ucontact_wrapper;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        ufirst_name = findViewById(R.id.edt_first_nameId);
        ulast_name = findViewById(R.id.edt_last_nameId);
        uemail = findViewById(R.id.edt_emailId);
        upassword = findViewById(R.id.edt_passwordId);
        uconfirm_password = findViewById(R.id.edt_confirm_passwordId);
        ucontact = findViewById(R.id.edt_contactId);

        ufirst_name_wrapper = findViewById(R.id.user_first_name_wrapper);
        ulast_name_wrapper = findViewById(R.id.user_last_name_wrapper);
        uemail_wrapper = findViewById(R.id.user_email_wrapper);
        upassword_wrapper = findViewById(R.id.user_password_wrapperId);
        uconfirm_password_wrapper = findViewById(R.id.user_confirm_password_wrapper);
        ucontact_wrapper = findViewById(R.id.user_contact_wrapper);

        findViewById(R.id.btn_registerId).setOnClickListener((View view)->{
            userRegister();
        });

    }

    private void userRegister() {

        if (mAuth.getCurrentUser()!=null){

            //user is logged in and can redirect to another activity
        }else {

            String first_name = ufirst_name.getText().toString().trim();
            String last_name = ulast_name.getText().toString().trim();
            String email = uemail.getText().toString().trim();
            String password = upassword.getText().toString().trim();
            String confirm_password = uconfirm_password.getText().toString().trim();
            String contact = ucontact.getText().toString().trim();

            if (first_name.isEmpty()) {
                ufirst_name_wrapper.setError("Enter First name");
                ufirst_name_wrapper.requestFocus();
                return;
            }
            if (last_name.isEmpty()) {
                ulast_name_wrapper.setError("Enter Last name");
                ulast_name_wrapper.requestFocus();
                return;
            }
            if (email.isEmpty()) {
                uemail_wrapper.setError("Enter Email");
                uemail_wrapper.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                upassword_wrapper.setError("Enter Password");
                upassword_wrapper.requestFocus();
                return;
            }
            if (!password.equals(confirm_password)) {
                uconfirm_password_wrapper.setError("Password didn't match");
                uconfirm_password_wrapper.requestFocus();
                return;
            }
            if (contact.isEmpty()) {
                ucontact_wrapper.setError("Enter Contact Number");
                ucontact_wrapper.requestFocus();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        User user = new User(first_name, last_name, email, contact);
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "User Register successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                }else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
