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

public class SignIn extends AppCompatActivity {

    private Button signIn;
    private TextView signUp;
    private EditText et_email;
    private EditText et_password;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn = findViewById(R.id.btn_signin);
        signUp = findViewById(R.id.tv_signup);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(SignIn.this);



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCrededentials();

            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

    }

    private void checkCrededentials() {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

         if (email.isEmpty() || !email.contains("@"))
        {
            showError(et_email, "Email is not valid!");
        }
        else if (password.isEmpty()||password.length()<7)
        {
            showError(et_password," Password must be seven character");
        }

        else {

            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait while checking credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        mLoadingBar.dismiss();
                        Intent intent = new Intent(SignIn.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignIn.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}