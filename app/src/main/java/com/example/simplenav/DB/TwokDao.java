package com.example.simplenav.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.simplenav.CommucationController.GetPicture;
import com.example.simplenav.CommucationController.GetTwok;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface TwokDao {
//    @Insert
//    void insertAll(Twok...twoks);

    @Insert
    ListenableFuture<Void> insertAll(Twok...twoks);

//    @Query("SELECT * FROM twok")
//    List<Twok> getAll();

    @Query("SELECT * FROM twok")
    ListenableFuture<List<Twok>> getAll();

    @Query("SELECT * FROM twok WHERE uid IN (:userIds)")
    List<Twok> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM twok WHERE uid == :uid")
    Twok findByName(int uid);

    @Delete
    void delete(Twok twok);

    ///Aggiornate giuste

    @Query("SELECT * FROM Twok WHERE uid = :uid")
    ListenableFuture<Twok> loadUserById(int uid);

}
