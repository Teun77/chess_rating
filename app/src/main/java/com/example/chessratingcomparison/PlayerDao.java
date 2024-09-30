package com.example.chessratingcomparison;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface PlayerDao {
    @Query("SELECT * FROM Player ORDER BY rapidRating DESC")
    LiveData<List<Player>> getAllPlayersRapid();

    @Query("SELECT * FROM Player ORDER BY blitzRating DESC")
    LiveData<List<Player>> getAllPlayersBlitz();

    @Query("SELECT * FROM Player ORDER BY bulletRating DESC")
    LiveData<List<Player>> getAllPlayersBullet();

    @Query("SELECT * FROM Player ORDER BY puzzleRating DESC")
    LiveData<List<Player>> getAllPlayersPuzzle();

    @Query("SELECT * FROM Player ORDER BY country DESC")
    LiveData<List<Player>> getAllPlayersCountry();

    @Query("SELECT * FROM Player WHERE Player.playerId=:id")
    LiveData<Player> getPlayer(long id);

    @Insert
    void addPlayer(Player player);

    @Query("UPDATE Player SET name=:name, rapidRating=:rapidRating, blitzRating=:blitzRating, bulletRating=:bulletRating, puzzleRating=:puzzleRating, imageURL=:imageURL WHERE playerId=:id")
    void updatePlayer(long id, String name, int rapidRating, int blitzRating, int bulletRating, int puzzleRating, String imageURL);

    @Query("DELETE FROM Player WHERE Player.playerId=:playerId")
    void deletePlayer(long playerId);

    @Insert
    void addNote(Note note);

    @Query("DELETE FROM Note WHERE noteId=:noteId")
    void deleteNote(long noteId);

    @Query("UPDATE Note SET title=:title, text=:text WHERE noteId=:noteId")
    void updateNote(long noteId, String title, String text);

    @Query("SELECT * FROM Note LEFT JOIN Player ON Player.playerId=Note.playerId WHERE Player.playerId=:playerId")
    LiveData<List<Note>> getAllNotes(long playerId);
}