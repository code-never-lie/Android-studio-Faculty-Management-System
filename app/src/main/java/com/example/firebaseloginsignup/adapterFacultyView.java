package com.example.firebaseloginsignup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class adapterFacultyView extends ArrayAdapter<getterSetterClass> {

    private Activity context;
    private List<getterSetterClass> arrayList;

    public adapterFacultyView(Activity context, List<getterSetterClass> arrayList){
     super(context, R.layout.view_faculty, arrayList);
      this.context = context;
      this.arrayList = arrayList;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.view_faculty, null, true);

        TextView Code = listViewItem.findViewById(R.id.textViewCode);
        TextView Name = listViewItem.findViewById(R.id.textViewName);
        TextView Phone = listViewItem.findViewById(R.id.textViewPhone);
        TextView Department = listViewItem.findViewById(R.id.textViewDepartment);

        getterSetterClass getterSetterClass = arrayList.get(position);
        Code.setText(getterSetterClass.getCode());
        Name.setText(getterSetterClass.getName());
        Phone.setText(getterSetterClass.getPhone());
        Department.setText(getterSetterClass.getDepartment());
               return listViewItem;
    }
}
