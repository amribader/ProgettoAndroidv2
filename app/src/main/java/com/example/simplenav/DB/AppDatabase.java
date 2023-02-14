package com.example.simplenav.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Twok.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TwokDao twokDao();
}
