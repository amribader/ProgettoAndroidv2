package com.example.simplenav.CommucationController;

import com.example.simplenav.DB.PictureDB.Sid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";

    @POST("getTwok")
    Call<GetTwok> getTwok(@Body GetTwok sid);

    @POST("getTwok2")
    Call<GetTwok> getTwok2(@Body GetTwok sid);

//    @POST("getPicture")
//    Call<GetPicture> getPicture(@Body String body);

    @FormUrlEncoded
    @POST("getPicture")
    Call<GetPicture> getPicture(@Field("sid") String sid, @Field("uid") String uid);

//    @FormUrlEncoded
//    @POST("setProfile")
//    void setProfile(@Field("sid") String sid, @Field("name") String name, @Field("picture") String picture);

    @POST("setProfile")
    Call<Void> setProfile(@Body setProfileI profile);
    
    @POST("getProfile")
    Call<getProfileO> getProfile(@Body GetTwok sid);

    @POST("getFollowed")
    Call<List<getProfileO>> getFollowed(@Body GetTwok sid);

    @FormUrlEncoded
    @POST("isFollowed")
    Call<follow> isFollowed(@Field("sid") String sid, @Field("uid") String uid);

    @FormUrlEncoded
    @POST("unfollow")
    Call<Void> unfollow(@Field("sid") String sid, @Field("uid") String uid);

    @FormUrlEncoded
    @POST("follow")
    Call<Void> follow(@Field("sid") String sid, @Field("uid") String uid);

    @POST("addTwok")
    Call<Void> addTwok(@Body GetTwok twok);


    @POST("register")
    Call<Sid>getSid();

    @POST("setLike")
    Call<Void> setLike(@Body Like like);

}
