package com.example.chessratingcomparison;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Player.class,
        parentColumns = "playerId",
        childColumns = "playerId",
        onDelete = ForeignKey.CASCADE)
    })
public class Note {
    @PrimaryKey(autoGenerate = true)
    public long noteId;
    public String title;
    public String text;
    public long playerId;

    public Note(String title, String text, long playerId){
        this.title = title;
        this.text = text;
        this.playerId = playerId;
    }
}

