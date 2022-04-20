package com.example.firebaseloginsignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HODPanel extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    TextView textView;
    Button buttonLogout;
    Button trackYourslef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hodpanel);


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()== null){
            finish();
            Intent i = new Intent(getApplicationContext(),loginActivity.class);
            startActivity(i);
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
       textView = findViewById(R.id.textViewYourEmail);
       textView.setText("Your email address is "+ user.getEmail());
       buttonLogout = findViewById(R.id.buttonLogOut);

       trackYourslef = findViewById(R.id.buttonTrack);
       buttonLogout.setOnClickListener(this);
         trackYourslef.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    if (v == buttonLogout){
        firebaseAuth.signOut();
        Intent i = new Intent(getApplicationContext(),loginActivity.class);
        startActivity(i);
    }

        if (v == trackYourslef){
            firebaseAuth.signOut();

            Intent i = new Intent(getApplicationContext(),TrackYourself.class);
            startActivity(i);
        }


    }
}
