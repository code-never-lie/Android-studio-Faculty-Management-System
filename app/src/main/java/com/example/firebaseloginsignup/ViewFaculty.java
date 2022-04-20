package com.example.firebaseloginsignup;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewFaculty extends AppCompatActivity {

    ListView listViewFaculty;
    List <getterSetterClass> facultyList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty);

        listViewFaculty = findViewById(R.id.listViewFaculty);


        facultyList= new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Faculty");


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                facultyList.clear();
               for (DataSnapshot facultySnapshot: dataSnapshot.getChildren() ){
                   getterSetterClass getterSetterClass = facultySnapshot.getValue(getterSetterClass.class);
                   facultyList.add(getterSetterClass);
               }
               adapterFacultyView adapter = new adapterFacultyView(ViewFaculty.this, facultyList);
               listViewFaculty.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewFaculty.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                getterSetterClass getterSetterClass = facultyList.get (position);
                 showUpdateDialog(getterSetterClass.getId(), getterSetterClass.getCode(), getterSetterClass.getName(), getterSetterClass.getPhone(), getterSetterClass.getDepartment());
                return false;
            }
        });

    }

    private void showUpdateDialog (final String id, String code, String name, String phone, String department){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View diallogView = inflater.inflate(R.layout.update_dialogue,null);
        dialogBuilder.setView(diallogView);

        final  EditText editTextCode = diallogView.findViewById(R.id.editTextCode);
        final EditText editTextName = diallogView.findViewById(R.id.editTextName);
        final EditText editTextPhone = diallogView.findViewById(R.id.editTextPhone);
        final Spinner spinnerDepartment = diallogView.findViewById(R.id.spinnerDepartment);
        final Button buttonUpadte = diallogView.findViewById(R.id.buttonUpdate);
        final Button buttonDelte = diallogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle("Updating Faculty " + name );

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpadte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = editTextCode.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String department = spinnerDepartment.getSelectedItem().toString();
                updateArtist(id, code, name,phone, department);
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



    private Boolean updateArtist ( String id, String code, String name, String phone, String department){

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Faculty").child(id);
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
