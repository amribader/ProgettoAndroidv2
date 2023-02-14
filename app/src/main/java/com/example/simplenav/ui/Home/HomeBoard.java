package com.example.simplenav.ui.Home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplenav.R;

public class HomeBoard extends Fragment {

    private HomeBoardViewModel mViewModel;

    public static HomeBoard newInstance() {
        return new HomeBoard();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_board, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeBoardViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        TwoksRepository twoksRepository = new TwoksRepository();
//        twoksRepository.initWithFakeData();
//        RecyclerView recyclerView = view.findViewById(R.id.twokRecyclerView);
//        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        TwokListAdapter adapter = new TwokListAdapter(twoksRepository,getActivity());
//        recyclerView.setAdapter(adapter);
    }
}