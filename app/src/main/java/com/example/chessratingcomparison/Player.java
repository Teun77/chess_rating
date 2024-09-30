package com.example.chessratingcomparison;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Player {
    @PrimaryKey(autoGenerate = true)
    public long playerId;
    String name;
    int rapidRating;
    int blitzRating;
    int bulletRating;
    int puzzleRating;
    String imageURL;
    String country;

    public Player(String name, int rapidRating, int blitzRating, int bulletRating, int puzzleRating, String imageURL, String country) {
        this.name = name;
        this.rapidRating = rapidRating;
        this.blitzRating = blitzRating;
        this.bulletRating = bulletRating;
        this.puzzleRating = puzzleRating;
        this.imageURL = imageURL;
        this.country = country;
    }

    public long getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getRapidRating() {
        return rapidRating;
    }

    public int getBlitzRating() {
        return blitzRating;
    }

    public int getBulletRating() {
        return bulletRating;
    }

    public int getPuzzleRating() {
        return puzzleRating;
    }

    public String getCountry() {
        return country;
    }
}
