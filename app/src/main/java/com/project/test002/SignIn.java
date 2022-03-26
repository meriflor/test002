package com.project.test002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    private Button signIn;
    private TextView signUp;
    private EditText loginEmail;
    private EditText loginPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn = findViewById(R.id.btn_signin);
        loginEmail= findViewById(R.id.et_email);
        loginPass= findViewById(R.id.et_password);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            if(validateData()){

                    Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }



            }
        });


        signUp = findViewById(R.id.tv_signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

    }

    private boolean validateData(){

       /* if(loginEmail.getText().toString().isEmpty()){
            loginEmail.setError("Enter email address");
            return false;
        }*/

        if(!Patterns.EMAIL_ADDRESS.matcher(loginEmail.getText().toString()).matches()){
            loginEmail.setError("Invalid Email address");
            return false;
        }
        if(loginPass.getText().toString().isEmpty()){
           loginPass.setError("Enter password");
           return false;
        }
        return true;
    }
}