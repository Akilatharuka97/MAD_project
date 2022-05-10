package com.example.cocototo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdampter extends RecyclerView.Adapter<MyAdampter.MyViewHolder> {

    Context context;
    ArrayList<User> userArrayList;

    public MyAdampter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyAdampter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdampter.MyViewHolder holder, int position) {

        User user = userArrayList.get(position);

        holder.shopname.setText(user.shopname);
        holder.telephone.setText(user.telephone);
        holder.address.setText(user.address);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView shopname, telephone, address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            shopname = itemView.findViewById(R.id.shopname);
            telephone = itemView.findViewById(R.id.telephone);
            address = itemView.findViewById(R.id.address);
        }
    }
}
