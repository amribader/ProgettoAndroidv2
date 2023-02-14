package com.example.simplenav;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.simplenav.CommucationController.RetrofitClient;
import com.example.simplenav.CommucationController.GetTwok;
import com.example.simplenav.CommucationController.communicationController;
import com.example.simplenav.CommucationController.follow;
import com.example.simplenav.CommucationController.followI;
import com.example.simplenav.CommucationController.isFollowed;
import com.example.simplenav.DB.PictureDB.Sid;
import com.example.simplenav.ui.Home.TwokListAdapter;
import com.example.simplenav.ui.Home.TwoksRepository;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends Fragment {

    public static final String mypreference = "mypref";

    private final TwoksRepository twoksRepository = new TwoksRepository();
    private ViewPager2 viewPager2;
//    private TwoksRepository twoksRepository = null;
    //private TwoksRepository twoksRepository = new ViewModelProvider(this).get(TwoksRepository.class);


    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //sid
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        System.err.println("HOME prova SID"+sharedpreferences.contains("sid"));
        System.err.println("HOME prova SID"+sharedpreferences.getString("sid",""));

        Sid sid = new Sid();
        sid.setSid(sharedpreferences.getString("sid",""));


        //todo parte nuova passaggio parametri

        String s = getArguments().getString("uid");
        int i = getArguments().describeContents();
//        int x = getArguments().get(String.valueOf("uid"));
        int uid = getArguments().getInt("uid");

        System.err.println("HOME s ="+s+ "  "+i +"  - "+uid);
        //int amount = ConfirmationFragmentArgs.fromBundle(getArguments()).getAmount();


        //codice funzionante primo modo prof
//        TwoksRepository twoksRepository = new TwoksRepository();
//        twoksRepository.initWithFakeData();
//        RecyclerView recyclerView = view.findViewById(R.id.viewPager2);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        TwokListAdapter adapter = new TwokListAdapter(twoksRepository,getActivity());
//        recyclerView.setAdapter(adapter);



        //////////secondo modo effetto magnetico

        //getOneTwok();

        //TwoksRepository twoksRepository = new TwoksRepository();
        //twoksRepository = new ViewModelProvider(this).get(TwoksRepository.class);

        //twoksRepository.getTwoks();
        //twoksRepository.initWithFakeData();
//        twoksRepository.getTwoks().observe(getViewLifecycleOwner(),twoks ->{
//            //UpdateUI
//        });
        //twoksRepository.getOneTwok();
        //twoksRepository.getTwoks();
        //twoksRepository.initWithFakeData();
//        twoksRepository.getTwoks();
//        twoksRepository.initWithFakeData();


        viewPager2 = view.findViewById(R.id.viewPager2);

        Button button = view.findViewById(R.id.Follow);

        if(uid==-1){
            button.setVisibility(View.GONE);
            twoksRepository.getOneTwok(viewPager2,sid,getContext());
        }else{
            button.setVisibility(View.VISIBLE);
            twoksRepository.getTwokByUid(viewPager2,uid,sid);

            //vado a chiamare il metodo is follower
            communicationController.isFollowed(sid.getSid(), uid, new isFollowed() {
                @Override
                public void isFollowed(follow body) {
                    System.err.println("Home body 111"+ body);

                    if(body.getFollowed()){
                        //true imposta scritto smetti di seguire
                        button.setText("UNFOLLOW");
                    }else {
                        //false imposta scritta segui
                        button.setText("FOLLOW");
                    }

                    button.setOnClickListener(view1 -> {
                        if (body.getFollowed()){
                            //true codice per smettere di seguire questo tizio
                            //chiamate di rete
                            communicationController.unfollow(sid.getSid(), uid, new followI() {
                                @Override
                                public void follow(Void body) {
                                    System.err.println("130 HOME follow"+body);
                                }
                            });
                            //do un alert che ho smesso di seguirlo
                            //cambio la scritta del testo in segui
                            button.setText("FOLLOW");
                        }else{
                            //todo attenzioni serve veramente questo ramo??
                            communicationController.follow(sid.getSid(), uid, body1 -> System.err.println("130 HOME follow"+ body1));
                            //do un alert che ho smesso di seguirlo
                            //cambio la scritta del testo in segui
                            button.setText("UNFOLLOW");
                        }
                    });

                }
            });

            //impostare la scritta sul bottone

            //on button click in base alle scritta con un if vado a gestire i casi


        }


        System.err.println("GETONETWOKDALLAHOME");
        TwokListAdapter adapter = new TwokListAdapter(twoksRepository,getActivity());
        viewPager2.setAdapter(adapter);




        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Log.d("Home","onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("Home","onPageSelected");
                //twoksRepository.add("Ciao");
                //twoksRepository.getOneTwok();


                //todo gestire mostra oppre il pulsante
                if(uid==-1){
                    twoksRepository.getOneTwok(viewPager2,sid,getContext());
                }else{
                    twoksRepository.getTwokByUid(viewPager2,uid,sid);
                }

                //twoksRepository.getOneTwok(viewPager2);
                System.err.println("GETONETWOKONPAGESELECTED");
                //TwokListAdapter adapter = new TwokListAdapter(twoksRepository,getActivity());
                //viewPager2.setAdapter(adapter);
                //twoksRepository.getTwoks();
                //twoksRepository.getOneTwok();
                //getOneTwok();
            }
        });

    }

//    private void getOneTwok(ViewPager2 viewPager2){
//
//        GetTwok getTwok = new GetTwok();
//
//        //getTwok.setSid("qaKOeIk1DhEvBLOruWaR");
//
//        Call<GetTwok> call = RetrofitClient.getInstance().getMyApi().getTwok(getTwok);
//
//        call.enqueue(new Callback<GetTwok>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onResponse(@NonNull Call<GetTwok> call, @NonNull Response<GetTwok> response) {
//                System.err.println("Response");
//                switch (response.code()){
//                    case 400 :
//                        System.err.println("Error 400 - Client Error"+response.code());
//                        break;
//                    case 200 :
//                        System.err.println("Success 200 - "+response.body());
//                        twoksRepository.add(response.body());
//                        viewPager2.getAdapter().notifyDataSetChanged();
//                        break;
//                    case 500 :
//                        System.err.println("Error 500 - Client Error"+response.code());
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<GetTwok> call, @NonNull Throwable t) {
//                System.err.println("ERROR");
//                t.printStackTrace();
//            }
//        });
//    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("onPause","onPause");
        getArguments().clear();
        getArguments().putInt("uid",-1);
        //getArguments().getBundle("uid").set
    }
}