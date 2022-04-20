package com.example.firebaseloginsignup;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewCourses extends AppCompatActivity {

    List<getterSetterClass> courseList;
    DatabaseReference databaseReference;

    ListView  Courses;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);

        Courses = findViewById(R.id.listViewCourses);

        courseList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Courses");

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                courseList.clear();
                for (DataSnapshot facultySnapshot : dataSnapshot.getChildren()) {
                    getterSetterClass getterSetterClass = facultySnapshot.getValue(getterSetterClass.class);
                    courseList.add(getterSetterClass);
                }
                adapterFacultyView adapter = new adapterFacultyView(ViewCourses.this, courseList);
                Courses.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
