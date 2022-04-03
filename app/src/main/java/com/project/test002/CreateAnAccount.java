package com.project.test002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateAnAccount extends AppCompatActivity {

    private TextView signIn;
    private EditText et_fullName;
    private EditText et_email;
    private EditText et_password;
    private EditText et_retype_password;
    private Button btn_signup;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);


        signIn = findViewById(R.id.tv_signin);
        et_fullName = findViewById(R.id.et_fullName);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_retype_password = findViewById(R.id.et_retype_password);
        btn_signup = findViewById(R.id.btn_signup);
        mAuth = FirebaseAuth.getInstance();
        DbQuery.g_firestore = FirebaseFirestore.getInstance();
        mLoadingBar = new ProgressDialog(CreateAnAccount.this);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrededentials();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private void checkCrededentials() {
        String userName = et_fullName.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String conformPassword = et_retype_password.getText().toString();

        if (userName.isEmpty() || userName.length() < 7) {
            showError(et_fullName, "Your user name is not valid!");

        }
        else if (email.isEmpty() || !email.contains("@"))
        {
            showError(et_email, "Email is not valid!");
        }
        else if (password.isEmpty()||password.length()<7)
        {
            showError(et_password," Password must be seven character");
        }
        else if(conformPassword.isEmpty()||!conformPassword.equals(password))
        {
            showError(et_retype_password,"Not match");
        }
        else {
            mLoadingBar.setTitle("Register");
            mLoadingBar.setMessage("Please wait while checking your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();
           // Toast.makeText(this,"Call registration method",Toast.LENGTH_SHORT).show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(CreateAnAccount.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                       DbQuery.createUserData(email, userName, new MyCompleteListener(){

                           @Override
                           public void onSuccess() {

                               mLoadingBar.dismiss();
                               Intent intent = new Intent(CreateAnAccount.this, Teacher_Homepage.class);
                               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                               startActivity(intent);
                           }

                           @Override
                           public void onFailure() {
                                Toast.makeText(CreateAnAccount.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
                               mLoadingBar.dismiss();
                           }
                       });

                    }
                    else{
                        Toast.makeText(CreateAnAccount.this, task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }
                }



              }
            );
        }
    }


    private void showError(EditText input, String s) {

        input.setError(s);
        input.requestFocus();
    }
}

