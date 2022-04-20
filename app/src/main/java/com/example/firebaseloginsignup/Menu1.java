package com.example.firebaseloginsignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Menu1 extends AppCompatActivity implements View.OnClickListener {


    Button button1;
    Button button2;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);


        button1 = findViewById(R.id.buttonProfile);
        button2 = findViewById(R.id.buttonFaculty);
        button3 = findViewById(R.id.buttonCourses);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
          if (v==button1){
              Intent i = new Intent(getApplicationContext(), HODPanel.class);
              startActivity(i);
          }
        if (v==button2){
            Intent i = new Intent(getApplicationContext(), FacultyPanel.class);
            startActivity(i);
        }

        if (v==button3){
            Intent i = new Intent(getApplicationContext(), CoursesPanel.class);
            startActivity(i);
        }

    }
}
