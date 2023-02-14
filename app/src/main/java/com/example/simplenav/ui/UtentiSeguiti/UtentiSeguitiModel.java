package com.example.simplenav.ui.UtentiSeguiti;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenav.CommucationController.GetPicture;
import com.example.simplenav.CommucationController.communicationController;
import com.example.simplenav.CommucationController.getPictureI;
import com.example.simplenav.CommucationController.getProfileO;
import com.example.simplenav.DB.PictureDB.ErrorPictureDBListener;
import com.example.simplenav.DB.PictureDB.PictureDBListener;
import com.example.simplenav.DB.PictureDB.PictureRepository;
import com.example.simplenav.DB.PictureDB.Sid;
import com.example.simplenav.R;

import java.util.ArrayList;
import java.util.List;

public class UtentiSeguitiModel extends ViewModel {

    //per il sid


        private List<getProfileO> follower = null;

        public static final String mypreference = "mypref";

        public UtentiSeguitiModel() {
            this.follower = new ArrayList<getProfileO>();
            //getOneTwok();
//            communicationController.getFollowed(new getFollowed() {
//                @Override
//                public void getFollow(List<getProfileO> body) {
//                    follower = body;
//                    System.err.println("body="+body);
//                }
//            });
            //System.err.println(getFollower());
        }

        public getProfileO getFollower(int pos) {
            return follower.get(pos);
        }

        public int getSize() {
            return follower.size();
        }

        public void setFollower(List<getProfileO> follower) {
            this.follower = follower;
        }

        public void initfakedata(){
            for (int i = 0; i < 10; i++) {
                getProfileO getProfileO = new getProfileO();
                String s = "ciao"+i+"";
                getProfileO.setName(s);
                follower.add(getProfileO);
            }
        }

    public List<getProfileO> getFollower() {
        return follower;
    }

    public void getOneTwok(RecyclerView rv, Context context) {

        SharedPreferences sharedpreferences = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        System.err.println("prova SID"+sharedpreferences.contains("sid"));
        System.err.println("prova SID"+sharedpreferences.getString("sid",""));

        Sid sid = new Sid();
        sid.setSid(sharedpreferences.getString("sid",""));

            communicationController.getFollowed(body -> {
                System.err.println("responso utenti seguiti"+body);
                System.err.println("responso utenti seguiti"+body.size());

                //controllo che la lunghezza dell'array sia diversa da zero
                //se l'array è vuoto vuol dire che non segue nessuno

                if(body.size()==0){
                    //cioè l'array è vuoto quindi non seguo nessuno
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setMessage("NON SEGUI NESSUN UTENTE!!");
                    alertDialogBuilder.setPositiveButton("Vuoi tornare alla bacheca??", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //codice per mandarlo in bacheca
                            //Navigation.findNavController().navigate(R.id.action_home_to_mapsFragment);
                        }
                    });
//                    alertDialogBuilder.setNegativeButton(CharSequence text,
//                            DialogInterface.OnClickListener listener)
                    AlertDialog alert = alertDialogBuilder.create();
                    //Setting the title manually
                    alert.setTitle("ATTENZIONE");
                    alert.show();
                }

                for (getProfileO x: body) {
            getProfileO user = new getProfileO();
                    System.err.println("body getTwok"+x);
                    //follower.add(x);
                    user.setName(x.getName());
                    user.setUid(x.getUid());

                    //todo
                    //Qui prima di fare la chiamata di rete controllo
                    // il db per verificare se sia presewnte o meno l'immagine profilo

                    //prendi il sid dalle preferences
                    //Sid sid = new Sid();
                    PictureRepository.getPicture(sid.getSid(), 1, x.getUid(), new PictureDBListener() {
                        @Override
                        public void onPictureReady(String picture) {
                            //qui gestisco immagine che ho ottenuto
                            System.err.println("USM+ getONeTwok getPicture"+body);
                            user.setPicture(picture);
                            follower.add(user);
                            rv.getAdapter().notifyDataSetChanged();
                        }
                    }, new ErrorPictureDBListener() {
                        @Override
                        public void onError(Throwable t) {
                            //gestisco il caso di errore
                            //todo
                        }
                    },context);



//                    communicationController.getPicture("qaKOeIk1DhEvBLOruWaR", x.getUid(), new getPictureI() {
//                        @Override
//                        public void getPicture(GetPicture body) {
//                            System.err.println("USM+ getONeTwok getPicture"+body);
//                            user.setPicture(body.getPicture());
//                            follower.add(user);
//                            rv.getAdapter().notifyDataSetChanged();
//                        }
//                    });//todo sid hardcoded
                }
                //setFollower(body);
                //System.err.println("follower2"+follower);
                //follower.add(user);
                //rv.getAdapter().notifyDataSetChanged();//funziona provo a spostarlo sopra
            },sid);
            //System.err.println("follower"+follower);
        }

//    public void fun(RecyclerView rv){
//        System.err.println("fun");
//        getOneTwok(rv);
//        System.err.println("ciao i follower sono"+follower);
//        for (getProfileO x : follower) {
//            System.err.println("dentro a fun"+x);
//            communicationController.getPicture(x.getSid(),x.getUid());
//        }
//    }

//    public void fun(){
//        System.err.println("fun");
//        System.err.println("ciao i follower sono"+follower);
//        for (getProfileO x : follower) {
//            System.err.println("dentro a fun"+x);
//            communicationController.getPicture("qaKOeIk1DhEvBLOruWaR",x.getUid());//todo sid hardcoded
//        }
//    }

    @Override
    public String toString() {
        return "UtentiSeguitiModel{" +
                "follower=" + follower +
                '}';
    }
}
