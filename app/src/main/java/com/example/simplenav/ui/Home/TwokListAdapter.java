package com.example.simplenav.ui.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenav.CommucationController.GetTwok;
import com.example.simplenav.R;

public class TwokListAdapter extends RecyclerView.Adapter<Twok_ViewHolder> {

    private TwoksRepository twoksRepository = null;
    private LayoutInflater mInflater = null;

    public TwokListAdapter(TwoksRepository twoksRepository, Context context) {
        this.twoksRepository = twoksRepository;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Twok_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//quando viene creata la cella
        View view = mInflater.inflate(R.layout.twok_view_holder,parent, false);
        return new Twok_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Twok_ViewHolder holder, int position) {//associare gli elementi alla cella
        //GetTwok twok = twoksRepository.getTwokList(position);
        GetTwok twok = twoksRepository.getTwokList(position);

        holder.updateContent(twok);
    }

    @Override
    public int getItemCount() {//quanti elementi la lista deve avere
        return twoksRepository.getSize();
    }
}
