package com.example.chessratingcomparison;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class, Note.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();
    static AppDatabase instance;

    static synchronized public AppDatabase getInstance(Context context) {
        if (instance==null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "chess-rating-db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
