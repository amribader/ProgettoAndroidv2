package com.example.simplenav.ui.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager2.widget.ViewPager2;

import com.example.simplenav.CommucationController.GetTwok;
import com.example.simplenav.CommucationController.RetrofitClient;
import com.example.simplenav.DB.PictureDB.Sid;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//MODEL O VIEWMODEL

public class TwoksRepository extends ViewModel {//è il model

    private List<GetTwok> twokList = null;

    private int tidSequence = 1;

    public TwoksRepository() {
        this.twokList = new ArrayList<GetTwok>();
        //getOneTwok();
    }

    public GetTwok getTwokList(int pos) {
        return twokList.get(pos);
    }

    public int getSize() {
        return twokList.size();
    }

    public void add(GetTwok twok) {
        twokList.add(twok);
    }

    public void getOneTwok(ViewPager2 viewPager2, Sid sid, Context context) {

        GetTwok getTwok = new GetTwok();
        getTwok.setSid(sid.getSid());

        getTwok.setTid(tidSequence);
        tidSequence++;
        Log.d("SID",sid.getSid());

        //getTwok.setSid("qaKOeIk1DhEvBLOruWaR");

        Call<GetTwok> call = RetrofitClient.getInstance().getMyApi().getTwok2(getTwok);

        call.enqueue(new Callback<GetTwok>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<GetTwok> call, @NonNull Response<GetTwok> response) {
                System.err.println("Response");
                switch (response.code()) {
                    case 400:
                        System.err.println("Error 400 - Client Error" + response.code());
                        break;
                    case 200:
                        System.err.println("Success 200 - " + response.body());

                        response.body().setSid(sid.getSid());
                        twokList.add(response.body());
                        viewPager2.getAdapter().notifyDataSetChanged();
                        break;
                    case 500:
                        System.err.println("Error 500 - Client Error" + response.code());
                        break;
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetTwok> call, @NonNull Throwable t) {
                System.err.println("ERROR");
                t.printStackTrace();
            }
        });
    }

    public void getTwokByUid(ViewPager2 viewPager2, int uid, Sid sid) {
        GetTwok getTwok = new GetTwok();
        getTwok.setUid(uid);
        getTwok.setSid(sid.getSid());
        System.err.println("Get Twok BY UID");

        System.err.println("get two"+ getTwok);
        Call<GetTwok> call = RetrofitClient.getInstance().getMyApi().getTwok(getTwok);

        call.enqueue(new Callback<GetTwok>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<GetTwok> call, @NonNull Response<GetTwok> response) {
                System.err.println("Response");
                System.err.println(response);

                switch (response.code()) {
                    case 400:
                        System.err.println("Error 400 - Client Error" + response.code());
                        break;
                    case 200:
                        System.err.println("Success 200 - " + response.body());
                        response.body().setSid(sid.getSid());
                        twokList.add(response.body());
                        viewPager2.getAdapter().notifyDataSetChanged();
                        break;
                    case 500:
                        System.err.println("Error 500 - Client Error" + response.code());
                        break;
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetTwok> call, @NonNull Throwable t) {
                System.err.println("ERROR");
                t.printStackTrace();
            }
        });
    }

}
//    public void getTwokPicture() {
//
//        GetTwok getTwok = new GetTwok();
//
//        //getTwok.setSid("qaKOeIk1DhEvBLOruWaR");
//
//        Call<GetTwok> call = RetrofitClient.getInstance().getMyApi().getPicture("","");
//
//        call.enqueue(new Callback<GetTwok>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onResponse(@NonNull Call<GetTwok> call, @NonNull Response<GetTwok> response) {
//                System.err.println("Response");
//                switch (response.code()) {
//                    case 400:
//                        System.err.println("Error 400 - Client Error" + response.code());
//                        break;
//                    case 200:
//                        System.err.println("Success 200 - " + response.body());
//                        //twokList.add(response.body());
//                        //viewPager2.getAdapter().notifyDataSetChanged();
//                        break;
//                    case 500:
//                        System.err.println("Error 500 - Client Error" + response.code());
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
//
//}
//    public void getOneTwok(){
//        System.err.println("getOneTwok");
//        GetTwok getTwok = new GetTwok();
//
//        getTwok.setSid("qaKOeIk1DhEvBLOruWaR");
//
//        Call<GetTwok> call = RetrofitClient.getInstance().getMyApi().getTwok(getTwok);
//
//        call.enqueue(new Callback<GetTwok>() {
//            @Override
//            public void onResponse(@NonNull Call<GetTwok> call, @NonNull Response<GetTwok> response) {
//                System.err.println("Response");
////                System.err.println(response);
////                System.err.println(response.code());
////                System.err.println(response.body());
//                switch (response.code()){
//                    case 400 :
//                        System.err.println("Error 400 - Client Error"+response.code());
//                        break;
//                    case 200 :
//                        System.err.println("Success 200 - "+response.body());
//                        twokList.add(response.body());
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
//
//
//    }
//}



//
//public class TwoksRepository extends ViewModel {//è il model
//
//    private List<String> twokList = null;
//    //private List<String> twokList = null;
//    private MutableLiveData<List<String>> twoks = null;
//
////    public MutableLiveData<List<String>> getTwoks() {
////        System.err.println("getTwoks");
////        if (twoks == null) {
////            System.err.println("ramo if");
////            twoks = new MutableLiveData<List<String>>();
////            loadTwoks();
////        }
////        twokList = new ArrayList<String>();
////        return twoks;
////    }
//
//    private void loadTwoks() {
//        // Do an asynchronous operation to fetch users.
//        getOneTwok();
//    }
//
//    public void addLD(String s){
//
//    }
//
////    public TwoksRepository() {
////        this.tw = new ArrayList<String>();
////    }
//
//    public String getTwokList(int pos) {
//        return twoks.getValue().get(pos);
//    }
//
//    public int getSize(){
//        return twoks.getValue().size();
//        //return twokList.size();
//    }
//
//    public void initWithFakeData(){
//        for (int i = 0; i < 1; i++) {
//            twokList.add("Entry Twok"+i);
//            twoks.setValue(twokList);
//            getOneTwok();
//        }
//    }
//
//    public void add(String twok){
//        twokList.add(twok);
//        twoks.setValue(twokList);
//    }
//
////    public void add(String twok){
////        twokList.add(twok);
////    }
//
////    public void add(GetTwok twok){
////        twokList.add(twok);
////    }
//
////    @Override
////    public String toString() {
////        return "TwoksRepository{" +
////                "twokList=" + twokList +
////                '}';
////    }
//
//    public void getOneTwok(){
//        System.err.println("getOneTwok");
//        GetTwok getTwok = new GetTwok();
//
//        getTwok.setSid("qaKOeIk1DhEvBLOruWaR");
//
//        Call<GetTwok> call = RetrofitClient.getInstance().getMyApi().getTwok(getTwok);
//
//        call.enqueue(new Callback<GetTwok>() {
//            @Override
//            public void onResponse(@NonNull Call<GetTwok> call, @NonNull Response<GetTwok> response) {
//                System.err.println("Response");
////                System.err.println(response);
////                System.err.println(response.code());
////                System.err.println(response.body());
//                switch (response.code()){
//                    case 400 :
//                        System.err.println("Error 400 - Client Error"+response.code());
//                        break;
//                    case 200 :
//                        System.err.println("Success 200 - "+response.body());
////                        getTwok = response.body();
//                        twokList.add(response.body().getText());
//                        System.err.println(response.body().getText());
//                        System.err.println("List+"+twokList.toString());
//                        twoks.setValue(twokList);
//                        System.err.println("TWOKS"+twoks);
//
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
//        //System.err.println("fineMetodo"+twokList.toString());
//
//    }
//}
