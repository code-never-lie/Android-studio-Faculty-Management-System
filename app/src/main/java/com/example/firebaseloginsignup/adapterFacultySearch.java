package com.example.firebaseloginsignup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class adapterFacultySearch extends RecyclerView.Adapter<adapterFacultySearch.myViewHolder>{



    ArrayList<getterSetterClass> list;
    private OnNoteListener monNoteListener;

    public adapterFacultySearch(ArrayList<getterSetterClass> list, OnNoteListener onNoteListener)
    {
        this.list = list;
        this.monNoteListener = onNoteListener;
    }




    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder, viewGroup,false);
        return new myViewHolder(view, monNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        myViewHolder.code.setText(list.get(i).getCode());
        myViewHolder.name.setText(list.get(i).getName());
        myViewHolder.phone.setText(list.get(i).getPhone());
        myViewHolder.department.setText(list.get(i).getDepartment());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView code, name, phone,  department;
        OnNoteListener onNoteListener;
        public myViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            code = itemView.findViewById(R.id.textViewOfCode);
            name = itemView.findViewById(R.id.textViewOfName);
            phone = itemView.findViewById(R.id.textViewOfPhone);
            department = itemView.findViewById(R.id.textViewOfDepartment);


            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
         onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick (int position);
    }
}
