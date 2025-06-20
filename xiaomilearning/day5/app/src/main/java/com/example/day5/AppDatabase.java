package com.example.day5;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GameInfo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    public abstract GameInfoDao gameInfoDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "game_db").build();
                }
            }
        }
        return INSTANCE;
    }
} 