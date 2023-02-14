package com.example.simplenav.CommucationController;

import com.example.simplenav.DB.PictureDB.Sid;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class communicationController {//implements Callback<GetPicture> {

    private GetPicture getPicture;
    private GetPicture obj;

    public communicationController(GetPicture getPicture) {
        this.getPicture = getPicture;
    }

    public communicationController() {

    }

    public GetPicture getGetPicture() {
        return getPicture;
    }

    public void setGetPicture(GetPicture getPicture) {
        System.err.println("metodo set" + getPicture);
        this.getPicture = getPicture;
    }


    public static void register(onSidReadyListener onSidReadyListener){
        Call<Sid> call = RetrofitClient.getInstance().getMyApi().getSid();
        call.enqueue(new Callback<Sid>() {
            @Override
            public void onResponse(Call<Sid> call, Response<Sid> response) {
                System.err.println("CommunicationController REGISTER SUCCESS");
                //todo fare i controlli sul code
                onSidReadyListener.onSidReasy(response.body());
            }

            @Override
            public void onFailure(Call<Sid> call, Throwable t) {
                System.err.println("Errore chiamata di rete CommunicationController REGISTER");
            }
        });
    }


//    public static void setProfile(String sid, String name, String picture, OnSetProfileListener onSetProfileListener){
    public static void setProfile(OnSetProfileListener onSetProfileListener, setProfileI profile){
//        Call<Void> call = RetrofitClient.getInstance().getMyApi().setProfile(sid,name,picture);
        Call<Void> call = RetrofitClient.getInstance().getMyApi().setProfile(profile);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                onSetProfileListener.onSetProfile(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public static void getProfile(getProfile onSetProfileListener,Sid sid){
        GetTwok getTwok = new GetTwok();
        getTwok.setSid(sid.getSid());
        Call<getProfileO> call = RetrofitClient.getInstance().getMyApi().getProfile(getTwok);
        call.enqueue(new Callback<getProfileO>() {
            @Override
            public void onResponse(Call<getProfileO> call, Response<getProfileO> response) {
                System.err.println("Response getProfile");
                System.err.println("geProle"+response);
                onSetProfileListener.onGetProfile(response.body());
            }

            @Override
            public void onFailure(Call<getProfileO> call, Throwable t) {
                System.err.println("Failure getProfile");
            }
        });

    }

    public static void getProfile2(getProfile onSetProfileListener){
        GetTwok getTwok = new GetTwok();
        Call<getProfileO> call = RetrofitClient.getInstance().getMyApi().getProfile(getTwok);
        call.enqueue(new Callback<getProfileO>() {
            @Override
            public void onResponse(Call<getProfileO> call, Response<getProfileO> response) {
                System.err.println("Response getProfile");
                System.err.println("geProle"+response);
                onSetProfileListener.onGetProfile(response.body());
            }

            @Override
            public void onFailure(Call<getProfileO> call, Throwable t) {
                System.err.println("Failure getProfile");
            }
        });

    }

    public static void getFollowed(getFollowed getFollowed,Sid sid){
        //questa riga server per ottenere il sid
        GetTwok getTwok = new GetTwok();
        getTwok.setSid(sid.getSid());
        Call<List<getProfileO>> call = RetrofitClient.getInstance().getMyApi().getFollowed(getTwok);
        call.enqueue(new Callback<List<getProfileO>>() {
            @Override
            public void onResponse(Call<List<getProfileO>> call, Response<List<getProfileO>> response) {
                getFollowed.getFollow(response.body());
            }

            @Override
            public void onFailure(Call<List<getProfileO>> call, Throwable t) {

            }
        });

    }

    public static void getPicture(String sid, int uid, getPictureI getPicture) {
        Call<GetPicture> call = RetrofitClient.getInstance().getMyApi().getPicture(sid,String.valueOf(uid));
        call.enqueue(new Callback<GetPicture>() {
            @Override
            public void onResponse(Call<GetPicture> call, Response<GetPicture> response) {
                //todo aggiungere controlli sul code e if rami
                getPicture.getPicture(response.body());
                System.err.println("sid"+sid+"uid"+uid);
                System.err.println("RESponse");
                System.err.println("CC getPicture"+response);
                System.err.println("CC getPicture"+response.body());
            }

            @Override
            public void onFailure(Call<GetPicture> call, Throwable t) {
                System.err.println("Failure");
            }
        });
    }

    public static void isFollowed(String sid, int uid,isFollowed isFollowed){
        System.err.println("is Followed");
        Call<follow> call = RetrofitClient.getInstance().getMyApi().isFollowed(sid,String.valueOf(uid));
        call.enqueue(new Callback<follow>() {
            @Override
            public void onResponse(Call<follow> call, Response<follow> response) {
                //todo fare tutti i controlli del caso 200 400 ecc
                System.err.println("RESponse is Followed");
                isFollowed.isFollowed(response.body());
                System.err.println("sid"+sid+"uid"+uid);
                System.err.println("CC isFollowed"+response);
                System.err.println("CC isFollowed"+response.body());
            }

            @Override
            public void onFailure(Call<follow> call, Throwable t) {
                System.err.println("Failure is Followed");
            }
        });
    }

    public static void follow(String sid, int uid, followI follow){
        System.err.println("follow");
        Call<Void> call = RetrofitClient.getInstance().getMyApi().follow(sid,String.valueOf(uid));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                follow.follow(response.body());
                //todo controeei sul vari caso
                System.err.println("RESponse  Follow");
                System.err.println("sid"+sid+"uid"+uid);
                System.err.println("CC Follow"+response);
                System.err.println("CC Follow"+response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.err.println("Failure Follow");
            }
        });
    }

    public static void unfollow(String sid, int uid, followI follow){
        System.err.println("unfollow");
        Call<Void> call = RetrofitClient.getInstance().getMyApi().unfollow(sid,String.valueOf(uid));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                follow.follow(response.body());//todo essendo void quest frase non serve
                //todo controeei sul vari caso
                System.err.println("RESponse  unfollow");
                System.err.println("sid"+sid+"uid"+uid);
                System.err.println("CC unfollow"+response);
                System.err.println("CC unfollow"+response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.err.println("Failure unfollow");
            }
        });
    }

    public static void addTwok(GetTwok twok, addTwokI addTwok){
        System.err.println("addTwok");
        Call<Void> call = RetrofitClient.getInstance().getMyApi().addTwok(twok);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.err.println("Response CC add Twok"+response);
                addTwok.addTwok(response);
                //todo fare i controlli crociati con resopnse code
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.err.println("Failure CC add Twok");
            }
        });
    }

    public static void setLike(Like like, LikeI likeI){
        System.err.println("setLike");
        Call<Void> call = RetrofitClient.getInstance().getMyApi().setLike(like);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //todo controlli sul code
                likeI.like(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}
//    public synchronized void getTwokPicture(String uid) {
////
////        GetTwok getTwok = new GetTwok();
////        getTwok.setUid(uid);
////
////        JSONObject paramObject = new JSONObject();
////        try {
////
////            paramObject.put("sid", "qaKOeIk1DhEvBLOruWaR");
////            paramObject.put("uid", uid);
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//
////
////        //getTwok.setSid("qaKOeIk1DhEvBLOruWaR");
////        System.err.println("da communicationcontroller get Picture " + getTwok);
//        Call<GetPicture> call = RetrofitClient.getInstance().getMyApi().getPicture("qaKOeIk1DhEvBLOruWaR", uid);
//        //call.enqueue(this);
//        call.enqueue(new Callback<GetPicture>() {
//            @Override
//            public void onResponse(Call<GetPicture> call, Response<GetPicture> response) {
//             obj = response.body();
//             setGetPicture(response.body());
//             //fun();
//            }
//
//            @Override
//            public void onFailure(Call<GetPicture> call, Throwable t) {
//
//            }
//        });
//    }
//
//    public GetPicture fun(){
//        return obj;
//    }
//
//    @Override
//    public void onResponse(Call<GetPicture> call, Response<GetPicture> response) {
//        //this.getPicture = response.body();
//        Log.d("onResponseMethod",response.body().toString());
//        setGetPicture(response.body());
//        System.err.println("GETPICTURE=="+getPicture);
//        System.err.println("Response");
//        Log.d("onResponsePicture", response.toString());
//        switch (response.code()) {
//            case 400:
//                System.err.println("Error 400 - Picture Client Error" + response.code());
//                break;
//            case 200:
//                System.err.println("Success PICTURE 200 - " + response.body());
//                break;
//            case 500:
//                System.err.println("Error 500 - Client Error" + response.code());
//                break;
//        }
//    }
//
//    @Override
//    public void onFailure(Call<GetPicture> call, Throwable t) {
//        System.err.println("ERROR");
//        t.printStackTrace();
//    }
//}

        //
//        call.enqueue(new Callback<GetTwok>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onResponse(@NonNull Call<GetTwok> call, @NonNull Response<GetTwok> response) {
//                System.err.println("Response");
//                Log.d("onResponsePicture",response.toString());
//                switch (response.code()) {
//                    case 400:
//                        System.err.println("Error 400 - Picture Client Error" + response.code());
//                        break;
//                    case 200:
//                        System.err.println("Success PICTURE 200 - " + response.body());
//                        //twokList.add(response.body());
//                        //viewPager2.getAdapter().notifyDataSetChanged();
//
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
//  //      });
//    }
//}
