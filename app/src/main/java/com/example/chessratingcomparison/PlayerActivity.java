package com.example.chessratingcomparison;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PlayerActivity extends AppCompatActivity {
    AppDatabase db;
    PlayerDao dao;
    long playerId;
    String playerName;
    int playerRapid;
    int playerBlitz;
    int playerBullet;
    int playerPuzzle;
    String playerCountry;
    String playerImageURL;
    ImageView playerAvatarImageView;
    TextView rapidRatingTextView;
    TextView blitzRatingTextView;
    TextView bulletRatingTextView;
    TextView puzzleRatingTextView;
    TextView playerCountryTextView;
    Button addOrDeleteButton;
    Button notesButton;
    public static int VIEW_PLAYER_NOTES_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        playerAvatarImageView = findViewById(R.id.playerAvatarImageView);
        rapidRatingTextView = findViewById(R.id.rapidRatingTextView);
        blitzRatingTextView = findViewById(R.id.blitzRatingTextView);
        bulletRatingTextView = findViewById(R.id.bulletRatingTextView);
        puzzleRatingTextView = findViewById(R.id.puzzleRatingTextView);
        playerCountryTextView = findViewById(R.id.countryPlayerTextView);
        addOrDeleteButton = findViewById(R.id.addOrDeleteButton);
        notesButton = findViewById(R.id.notesButton);


        db = AppDatabase.getInstance(this);
        dao = db.playerDao();

        Intent passedIntent = getIntent();
        if (passedIntent.hasExtra("id")) {
            playerId = passedIntent.getExtras().getLong("id");
            dao.getPlayer(playerId).observe(this, new Observer<Player>() {
                @Override
                public void onChanged(Player player) {
                    if (player == null) {
                        return;
                    }
//                    getSupportActionBar().setTitle(player.getName());
//                    rapidRatingTextView.setText(String.valueOf(player.getRapidRating()));
//                    blitzRatingTextView.setText(String.valueOf(player.getBlitzRating()));
//                    bulletRatingTextView.setText(String.valueOf(player.getBulletRating()));
//                    puzzleRatingTextView.setText(String.valueOf(player.getPuzzleRating()));
//                    playerCountryTextView.setText(player.getCountry());

                    addOrDeleteButton.setText("Delete");
                    addOrDeleteButton.setBackgroundColor(Color.RED);
                    playerName = player.getName();
                    playerRapid = player.getRapidRating();
                    playerBlitz = player.getBlitzRating();
                    playerBullet = player.getBulletRating();
                    playerPuzzle = player.getPuzzleRating();
                    playerCountry = player.getCountry();
                    playerImageURL = player.getImageURL();

                    getSupportActionBar().setTitle(playerName);
                    rapidRatingTextView.setText(String.valueOf(playerRapid));
                    blitzRatingTextView.setText(String.valueOf(playerBlitz));
                    bulletRatingTextView.setText(String.valueOf(playerBullet));
                    puzzleRatingTextView.setText(String.valueOf(playerPuzzle));
                    playerCountryTextView.setText(playerCountry);

                    if (playerImageURL != null) {
                        Picasso.get().load(playerImageURL).into(playerAvatarImageView);
                    }
                }
            });
            notesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToViewPlayerNotesActivity = new Intent(getApplicationContext(), PlayerNotesActivity.class);
                    goToViewPlayerNotesActivity.putExtra("playerId", playerId);
                    goToViewPlayerNotesActivity.putExtra("playerName", playerName);
                    startActivityForResult(goToViewPlayerNotesActivity, VIEW_PLAYER_NOTES_REQUEST_CODE);
                }
            });
        } else {
            if (passedIntent.hasExtra("playerName") && passedIntent.hasExtra("rapidRating")) {
                playerName = getIntent().getExtras().getString("playerName");
                playerRapid = getIntent().getExtras().getInt("rapidRating");
                playerBlitz = getIntent().getExtras().getInt("blitzRating");
                playerBullet = getIntent().getExtras().getInt("bulletRating");
                playerPuzzle = getIntent().getExtras().getInt("puzzleRating");
                playerImageURL = getIntent().getExtras().getString("imageURL");
                playerCountry = getIntent().getExtras().getString("country");
                addOrDeleteButton.setText("Add");
                addOrDeleteButton.setBackgroundColor(Color.GREEN);

                getSupportActionBar().setTitle(playerName);
                rapidRatingTextView.setText(String.valueOf(playerRapid));
                blitzRatingTextView.setText(String.valueOf(playerBlitz));
                bulletRatingTextView.setText(String.valueOf(playerBullet));
                puzzleRatingTextView.setText(String.valueOf(playerPuzzle));
                playerCountryTextView.setText(playerCountry);
                notesButton.setVisibility(View.INVISIBLE);

                if (playerImageURL != null) {
                    Picasso.get().load(playerImageURL).into(playerAvatarImageView);
                }
            }
        }

        addOrDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (passedIntent.hasExtra("id")) {
                            dao.deletePlayer(playerId);
                        } else {
                            Player newPlayer = new Player(playerName, playerRapid, playerBlitz, playerBullet, playerPuzzle, playerImageURL, playerCountry);
                            dao.addPlayer(newPlayer);
                        }
                        finish();
                    }
                }).start();
            }
        });
    }
}