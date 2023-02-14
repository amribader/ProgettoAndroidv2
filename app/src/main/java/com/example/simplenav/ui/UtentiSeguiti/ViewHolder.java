package com.example.simplenav.ui.UtentiSeguiti;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenav.CommucationController.getProfileO;
import com.example.simplenav.R;

public class ViewHolder extends RecyclerView.ViewHolder {//si adatta al loyout sigle line
    private final ImageView image;
    private final TextView text;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.profile_pic);
        text = itemView.findViewById(R.id.personName);

    }
    public void updateContent(getProfileO p){
//        image.setImageBitmap();
        text.setText(p.getName());

        System.err.println("VH"+p);

        byte[] decodedString = Base64.decode(p.getPicture(),Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
        image.setImageBitmap(decodeByte);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("uid", "500");
                bundle.putInt("uid",p.getUid());
                Navigation.findNavController(view).navigate(R.id.action_utentiSeguiti_to_home, bundle);
            }
        });

    }
}
