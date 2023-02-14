package com.example.simplenav.DB.PictureDB;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.room.Room;

import com.example.simplenav.CommucationController.GetPicture;
import com.example.simplenav.CommucationController.communicationController;
import com.example.simplenav.CommucationController.getPictureI;
import com.example.simplenav.DB.AppDatabase;
import com.example.simplenav.DB.Twok;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PictureRepository {

    private static final String TAG = "ROOMROOMEXAMPLE";
    public static void getPicture(String sid, int pversion,int uid, PictureDBListener pictureDBListener, ErrorPictureDBListener errorPictureDBListener, Context context){
        //controlla sul db se ce nella callback
            //nella collaback se ce chiama listenerr con immagine chimata
            //se non ce fai la chiamata di rete

        System.err.println("Lanciato metodo getPicture");

        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "databaseAndroid")
                .build();

        ListenableFuture<Twok> twokLF = db.twokDao().loadUserById(uid);
        try {
            Twok twok = twokLF.get();
            if(twok!=null){
                System.err.println("questa immagine è presente sul db");
                Log.d(TAG,"il twok cercato è"+twok);
                //è stato trovato sul db un user con questo uid
                //procedo valutando il pversion
                //se il pversion salvato sul db + minore del pversion del twok in questione
                    //vuol dire che immagine sul db va aggiornata quindi procedo facneo una chiamata di rete come sotto
                    //altriemnti se è maggione
                if (twok.getPversion() < pversion){
                    communicationController.getPicture(sid, uid, new getPictureI() {
                        @Override
                        public void getPicture(GetPicture body) {
                            System.err.println("USM+ getONeTwok getPicture"+body);

                            //operazioni da fare dopo la chiamata di rete
                            pictureDBListener.onPictureReady(body.getPicture());//todo qui dovrebbe essere un array di bayte
                            //chiamata alla interfaccia che gestirà immagine ora che ce la ho

                            //salvare sul db la immagine
                            //cosa da non fare todo
                            //body.getName(), body.getPicture(), body.getPversion(), uid
                            Twok p = new Twok(uid, body.getName(), body.getPversion(), body.getPicture());
                            db.twokDao().insertAll(p);
                            //faccio il contrario getAll per assicurrmi che sia stata salvata correttamente sul db
                            ListenableFuture<List<Twok>> twokList = db.twokDao().getAll();
                            twokList.addListener(()->{
                                try {
                                    List<Twok> twoks = twokList.get();
                                    for (Twok t : twoks) {
                                        Log.d(TAG,"59"+t);
                                    }
                                } catch (ExecutionException | InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            },context.getMainExecutor());

                            //log con lo stato dell'operazione
//                        user.setPicture(body.getPicture());
//                        follower.add(user);
//                        rv.getAdapter().notifyDataSetChanged();
                        }
                    });//todo sid hardcoded
                    System.err.println("immagine presene sul db è vecchia");
                }else{
                    pictureDBListener.onPictureReady(twok.getPicture());
                    System.err.println("immagine presene sul db è la pià recente");
                }

            }else{
                //non è stato trovato nessuno sul db con questo db
                //procedere facendo la chiamata di rete
                communicationController.getPicture(sid, uid, new getPictureI() {
                    @Override
                    public void getPicture(GetPicture body) {
                        System.err.println("USM+ getONeTwok getPicture"+body);

                        //operazioni da fare dopo la chiamata di rete
                        pictureDBListener.onPictureReady(body.getPicture());//todo qui dovrebbe essere un array di bayte
                        //chiamata alla interfaccia che gestirà immagine ora che ce la ho

                        //salvare sul db la immagine
                        //cosa da non fare todo
                        //body.getName(), body.getPicture(), body.getPversion(), uid
                        Twok p = new Twok(uid, body.getName(), body.getPversion(), body.getPicture());
                        db.twokDao().insertAll(p);
                        //faccio il contrario getAll per assicurrmi che sia stata salvata correttamente sul db
                        ListenableFuture<List<Twok>> twokList = db.twokDao().getAll();
                        twokList.addListener(()->{
                            try {
                                List<Twok> twoks = twokList.get();
                                for (Twok t : twoks) {
                                    Log.d(TAG,"59"+t);
                                }
                            } catch (ExecutionException | InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        },context.getMainExecutor());

                        //log con lo stato dell'operazione
//                        user.setPicture(body.getPicture());
//                        follower.add(user);
//                        rv.getAdapter().notifyDataSetChanged();
                    }
                });//todo sid hardcoded
            }

        } catch (ExecutionException | InterruptedException e) {
            errorPictureDBListener.onError(new RuntimeException(e));
            Toast toast = Toast.makeText(context, "Error picture not valid for "+uid, Toast.LENGTH_SHORT);
            toast.show();
            throw new RuntimeException(e);
        }


//        twokLF.addListener(() ->{
//            Log.d("PictureRepository-getPicture","Ho letto dal db user con uid");
//            try {
//                Twok twok = twokLF.get();
//
//                if (twok==null){
//                    //null cioè non è presente sul db
//                }else{
//                    Log.d("PictureRepository-getPicture","il twok letto è"+twok.toString());
//                    //pictureDBListener.onPictureReady(twok.getPicture());
//                }
//
//            } catch (ExecutionException | InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, context.getMainExecutor());

    }

}
