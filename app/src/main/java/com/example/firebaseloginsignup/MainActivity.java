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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseAuth firebaseAuth;

     EditText editTextEmail;
     EditText editTextPassword;
     Button buttonRegister;
     TextView textViewsignin;

     ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       firebaseAuth = FirebaseAuth.getInstance();

       if (firebaseAuth.getCurrentUser() != null){
           finish();
           Intent i = new Intent(getApplicationContext(),Menu1.class);
           startActivity(i);
       }
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewsignin = findViewById(R.id.textViewSigninn);

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);
        textViewsignin.setOnClickListener(this);

    }

    private void registerUser (){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;  // stops the further execution
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Registered Successfully",Toast.LENGTH_SHORT).show();


                            Intent i2 = new Intent(getApplicationContext(),loginActivity.class);
                            startActivity(i2);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Could not Register",Toast.LENGTH_SHORT).show();
                        }

                    }
        });

    }

    @Override
    public void onClick(View v) {

        if (v == buttonRegister){
             registerUser();
        }
        if (v==textViewsignin) {
           Intent i = new Intent (getApplicationContext(),loginActivity.class);
           startActivity(i);
        }

    }
}
