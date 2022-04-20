package com.example.firebaseloginsignup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText editTextCode;
    EditText editTextName;
    Spinner spinnerRoom;
    Spinner FacultySpinner ;
    Button buttonSaveCourse;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    showFacultySpinner showFacultySpinner; // declared Class object




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        showFacultySpinner = new showFacultySpinner(databaseReference);

        FacultySpinner = findViewById(R.id.spinnerAssignTeacher);
        spinnerRoom = findViewById(R.id.spinnerClassRoom);
        editTextCode = findViewById(R.id.editTextCourseCode);
        editTextName = findViewById(R.id.editTextCourseName);

        buttonSaveCourse = findViewById(R.id.buttonSaveCourse);

        FacultySpinner.setOnItemSelectedListener(this);

        databaseReference1 = FirebaseDatabase.getInstance().getReference("Courses");

       buttonSaveCourse.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               saveCourseData();
           }
       });


        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,showFacultySpinner.retreive());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FacultySpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void saveCourseData()  {
        String Code = editTextCode.getText().toString().trim();
        String Name = editTextName.getText().toString().trim();
        String Room = spinnerRoom.getSelectedItem().toString();
        String Faculty = FacultySpinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(Name)){
            String id =  databaseReference1.push().getKey();

          getterSetterClass getterSetterClass = new getterSetterClass(id, Code, Name, Room,Faculty );
            databaseReference1.child(id).setValue(getterSetterClass);
            Toast.makeText(this, "Course Added", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }
    }


}
