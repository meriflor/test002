package com.project.test002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAnAccount extends AppCompatActivity {

    private TextView signIn;
    private EditText name;
    private EditText signupEmail;
    private EditText signupPass;
    private EditText retypePass;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);


        signIn = findViewById(R.id.tv_signin);
        name = findViewById(R.id.et_fullName);
        signupEmail = findViewById(R.id.et_email);
        signupPass = findViewById(R.id.et_password);
        retypePass = findViewById(R.id.et_retype_password);
        signupButton = findViewById(R.id.btn_signup);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAnAccount.this, SignIn.class);
                startActivity(intent);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(validateData()) {
                    Intent intent = new Intent(CreateAnAccount.this, SignIn.class);
                    startActivity(intent);

                }
            }
        });
    }
    private boolean validateData(){
        if(name.getText().toString().isEmpty()){
            name.setError("Enter your name");
            return false;
        }
        if(signupEmail.getText().toString().isEmpty()) {
            signupEmail.setError(" Enter Email address");
        }
            if(!Patterns.EMAIL_ADDRESS.matcher(signupEmail.getText().toString()).matches()){
                signupEmail.setError(" Enter valid email address");
                return false;
            }


        if(signupPass.getText().toString().isEmpty()){
            signupPass.setError("Enter password");
            return false;

        }
        if(signupPass.getText().toString().length()<8){
            signupPass.setError("Password is too weak.");
            return false;

        }
        if (!retypePass.getText().toString().equals(signupPass.getText().toString())){
            retypePass.setError("Password does not match. Try again!" +
                    "");
            return false;
        }
        return true;
    }
}