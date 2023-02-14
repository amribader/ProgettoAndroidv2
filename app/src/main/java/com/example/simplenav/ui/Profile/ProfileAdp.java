package com.example.simplenav.ui.Profile;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenav.R;

import java.util.ArrayList;

public class ProfileAdp extends RecyclerView.Adapter<ProfileAdp.ViewHolder> {

    //initialize variable

    ArrayList<Uri> arrayList;

    public ProfileAdp(ArrayList<Uri> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main,parent,false);
        //pass viewHolder
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageURI(arrayList.get(position));
        String s = arrayList.get(position).toString();
        System.err.println("s value ="+s);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //initialize variable

        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
        }

        public void updateContent(){



        }

    }
}
