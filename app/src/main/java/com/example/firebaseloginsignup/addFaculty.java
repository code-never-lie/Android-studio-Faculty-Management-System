package com.example.firebaseloginsignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addFaculty extends AppCompatActivity {
    TextView textView;
    EditText editTextCode;
    EditText editTextName;
    EditText editTextPhone;
    Spinner spinnerDepartment;
    Button buttonSave;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        textView = findViewById(R.id.textViewFaculty);
        editTextCode = findViewById(R.id.editTextCode);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        spinnerDepartment = findViewById(R.id.spinnerDepartment);
        buttonSave = findViewById(R.id.buttonSave);




        databaseReference = FirebaseDatabase.getInstance().getReference("Faculty");

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

        private void saveData()  {
            String Code = editTextCode.getText().toString().trim();
            String Name = editTextName.getText().toString().trim();
            String Phone = editTextPhone.getText().toString().trim();
            String Faculty = spinnerDepartment.getSelectedItem().toString();

            if (!TextUtils.isEmpty(Name)){
             String id =  databaseReference.push().getKey();

           getterSetterClass getterSetterClass = new getterSetterClass(id, Code, Name, Phone, Faculty);
           databaseReference.child(id).setValue(getterSetterClass);
           Toast.makeText(this, "Faculty Added", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
            }
        }
}
