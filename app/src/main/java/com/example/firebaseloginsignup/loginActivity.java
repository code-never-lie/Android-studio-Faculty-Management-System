package com.example.firebaseloginsignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;

    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonSignIn;
    TextView textViewSignUp;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
           finish();
            Intent i = new Intent(getApplicationContext(),Menu1.class);
            startActivity(i);
        }

        editTextEmail =  findViewById(R.id.editTextEmaill);
        editTextPassword = findViewById(R.id.editTextPasswordd);
        buttonSignIn = findViewById(R.id.buttonSignin);
        textViewSignUp = findViewById(R.id.textViewSignUpp);

        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();



        progressDialog.setMessage("Checking credentials");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               progressDialog.dismiss();
                if (task.isSuccessful()){
                    finish();
                    Intent i = new Intent(getApplicationContext(), Menu1.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(loginActivity.this, "Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
       if (v == buttonSignIn){
           userLogin();
       }

       if (v == textViewSignUp) {
           Intent i = new Intent(getApplicationContext(), MainActivity.class);
           startActivity(i);
        }
    }
}
