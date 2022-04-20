package com.example.firebaseloginsignup;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class showFacultySpinner  {


DatabaseReference databaseReference;
Boolean saved= null;

    public showFacultySpinner(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public ArrayList<String> retreive(){

       final ArrayList<String> spacecrafts = new ArrayList<>();
       spacecrafts.add("Select Faculty Member");

       databaseReference.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               fetchData(dataSnapshot,spacecrafts);
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               fetchData(dataSnapshot,spacecrafts);

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
       return spacecrafts;

   }
   private void fetchData (DataSnapshot dataSnapshot, ArrayList<String> spacecrafts){
       spacecrafts.clear();

       for (DataSnapshot ds: dataSnapshot.getChildren()){
           String name = ds.getValue(getterSetterClass.class).getName();
           spacecrafts.add(name);
       }
   }
}
