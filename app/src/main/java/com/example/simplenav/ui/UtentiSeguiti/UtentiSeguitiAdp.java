package com.example.simplenav.ui.UtentiSeguiti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenav.CommucationController.GetTwok;
import com.example.simplenav.CommucationController.getProfileO;
import com.example.simplenav.R;
import com.example.simplenav.ui.Home.Twok_ViewHolder;
import com.example.simplenav.ui.Home.TwoksRepository;

public class UtentiSeguitiAdp extends RecyclerView.Adapter<ViewHolder> {

    private UtentiSeguitiModel followerRepository = null;
    private LayoutInflater mInflater = null;

    public UtentiSeguitiAdp(UtentiSeguitiModel followerRepository, Context context) {
        this.followerRepository = followerRepository;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_row_follower,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        getProfileO twok = followerRepository.getFollower(position);
        holder.updateContent(twok);
    }

    @Override
    public int getItemCount() {
        return followerRepository.getSize();
    }
}
