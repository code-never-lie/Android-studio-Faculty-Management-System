package com.example.firebaseloginsignup;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.SearchView;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FindFaculty extends AppCompatActivity implements adapterFacultySearch.OnNoteListener{

    SearchView searchView;
    RecyclerView recyclerView;

    DatabaseReference databaseReference;

    ArrayList <getterSetterClass> list;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_faculty);


       searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);


        databaseReference = FirebaseDatabase.getInstance().getReference ().child("Faculty");

    }


    @Override
    protected void onStart() {
        super.onStart();

        if (databaseReference!=null){
           databaseReference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   search1(dataSnapshot);


               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {
                   Toast.makeText(FindFaculty.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
               }
           });


        }


        if (searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    private void search1(DataSnapshot dataSnapshot){
        if (dataSnapshot.exists())
        {
            list = new ArrayList<>();
            for (DataSnapshot ds: dataSnapshot.getChildren()){
                list.add(ds.getValue(getterSetterClass.class));
            }
              adapterFacultySearch adapterFacultySearch = new adapterFacultySearch(list, this);
             recyclerView.setAdapter(adapterFacultySearch);
        }
    }


    private void search (String str)
    {

        ArrayList <getterSetterClass> myList = new ArrayList<>();
        for (getterSetterClass object: list){

            if (object.getCode().toLowerCase().contains(str.toLowerCase())){
                      myList.add(object);
            }
            adapterFacultySearch adapterFacultySearch = new adapterFacultySearch(myList,this);
            recyclerView.setAdapter(adapterFacultySearch);

        }
    }

    @Override
    public void onNoteClick(int position) {

        getterSetterClass getterSetterClass = list.get (position);
        showUpdateDialog(getterSetterClass.getId(), getterSetterClass.getCode(), getterSetterClass.getName(), getterSetterClass.getPhone(), getterSetterClass.getDepartment());

    }

    private void showUpdateDialog (final String id, String code, String name, String phone, String department) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View diallogView = inflater.inflate(R.layout.update_dialogue, null);
        dialogBuilder.setView(diallogView);

        final EditText editTextCode = diallogView.findViewById(R.id.editTextCode);
        final EditText editTextName = diallogView.findViewById(R.id.editTextName);
        final EditText editTextPhone = diallogView.findViewById(R.id.editTextPhone);
        final Spinner spinnerDepartment = diallogView.findViewById(R.id.spinnerDepartment);
        final Button buttonUpadte = diallogView.findViewById(R.id.buttonUpdate);
        final Button buttonDelte = diallogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle("Updating Faculty " + name);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpadte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = editTextCode.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String department = spinnerDepartment.getSelectedItem().toString();
                updateArtist(id, code, name, phone, department);
                alertDialog.dismiss();
            }
        });


        buttonDelte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFaculty(id);
            }
        });
    }
    private Boolean updateArtist ( String id, String code, String name, String phone, String department) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Faculty").child(id);
            getterSetterClass getterSetterClass = new getterSetterClass(id, code, name, phone, department);
            databaseReference.setValue(getterSetterClass);
            Toast.makeText(this, "Faculty Updated Successfully", Toast.LENGTH_LONG).show();
            return true;
    }

    private void deleteFaculty(String id) {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Faculty").child(id);
        databaseReference.removeValue();
    }
}
