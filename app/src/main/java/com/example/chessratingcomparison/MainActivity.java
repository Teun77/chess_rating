package com.example.chessratingcomparison;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView playerListView;
    PlayerDao dao;
    ItemAdapter itemAdapter;
    List<Player> players;
    TextView errorTextView;
    RequestQueue mQueue;
    EditText nameEditText;
    Button searchButton;
    Player searchedPlayer;
    TextView noPlayersTextView;
    TextView rapidTextView;
    TextView blitzTextView;
    TextView bulletTextView;
    TextView puzzleTextView;
    TextView countryTextView;
    public static int VIEW_PLAYER_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Chess Rating Comparison");

        rapidTextView = findViewById(R.id.mainRapidTextView);
        blitzTextView = findViewById(R.id.mainBlitzTextView);
        bulletTextView = findViewById(R.id.mainBulletTextView);
        puzzleTextView = findViewById(R.id.mainPuzzleTextView);
        countryTextView = findViewById(R.id.mainCountryTextView);
        playerListView = findViewById(R.id.PlayerListView);
        nameEditText = findViewById(R.id.nameEditText);
        searchButton = findViewById(R.id.searchButton);
        errorTextView = findViewById(R.id.errorTextView);
        noPlayersTextView = findViewById(R.id.noPlayersTextView);

        String apiString = "https://api.chess.com/pub/player/";
        mQueue = Volley.newRequestQueue(this);
        searchedPlayer = new Player(null, 0 , 0, 0, 0, null, null );

        AppDatabase db = AppDatabase.getInstance(this);
        dao = db.playerDao();

        Intent goToViewPlayer = new Intent(getApplicationContext(), PlayerActivity.class);

        dao.getAllPlayersRapid().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                itemAdapter = new ItemAdapter(MainActivity.this, players);
                playerListView.setAdapter(itemAdapter);
                if (players.size() == 0) {
                    noPlayersTextView.setVisibility(View.VISIBLE);
                } else noPlayersTextView.setVisibility(View.INVISIBLE);
            }
        });

        rapidTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.getAllPlayersRapid().observe(MainActivity.this, new Observer<List<Player>>() {
                    @Override
                    public void onChanged(List<Player> players) {
                        itemAdapter = new ItemAdapter(MainActivity.this, players);
                        playerListView.setAdapter(itemAdapter);
                        if (players.size() == 0) {
                            noPlayersTextView.setVisibility(View.VISIBLE);
                        } else noPlayersTextView.setVisibility(View.INVISIBLE);
                    }
                });
                rapidTextView.setTypeface(rapidTextView.getTypeface(), Typeface.BOLD_ITALIC);
                blitzTextView.setTypeface(blitzTextView.getTypeface(), Typeface.ITALIC);
                bulletTextView.setTypeface(bulletTextView.getTypeface(), Typeface.ITALIC);
                puzzleTextView.setTypeface(puzzleTextView.getTypeface(), Typeface.ITALIC);
                countryTextView.setTypeface(countryTextView.getTypeface(), Typeface.ITALIC);
            }
        });
        blitzTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.getAllPlayersBlitz().observe(MainActivity.this, new Observer<List<Player>>() {
                    @Override
                    public void onChanged(List<Player> players) {
                        itemAdapter = new ItemAdapter(MainActivity.this, players);
                        playerListView.setAdapter(itemAdapter);
                        if (players.size() == 0) {
                            noPlayersTextView.setVisibility(View.VISIBLE);
                        } else noPlayersTextView.setVisibility(View.INVISIBLE);
                    }
                });
                rapidTextView.setTypeface(rapidTextView.getTypeface(), Typeface.ITALIC);
                blitzTextView.setTypeface(blitzTextView.getTypeface(), Typeface.BOLD_ITALIC);
                bulletTextView.setTypeface(bulletTextView.getTypeface(), Typeface.ITALIC);
                puzzleTextView.setTypeface(puzzleTextView.getTypeface(), Typeface.ITALIC);
                countryTextView.setTypeface(countryTextView.getTypeface(), Typeface.ITALIC);
            }
        });
        bulletTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.getAllPlayersBullet().observe(MainActivity.this, new Observer<List<Player>>() {
                    @Override
                    public void onChanged(List<Player> players) {
                        itemAdapter = new ItemAdapter(MainActivity.this, players);
                        playerListView.setAdapter(itemAdapter);
                        if (players.size() == 0) {
                            noPlayersTextView.setVisibility(View.VISIBLE);
                        } else noPlayersTextView.setVisibility(View.INVISIBLE);
                    }
                });
                rapidTextView.setTypeface(rapidTextView.getTypeface(), Typeface.ITALIC);
                blitzTextView.setTypeface(blitzTextView.getTypeface(), Typeface.ITALIC);
                bulletTextView.setTypeface(bulletTextView.getTypeface(), Typeface.BOLD_ITALIC);
                puzzleTextView.setTypeface(puzzleTextView.getTypeface(), Typeface.ITALIC);
                countryTextView.setTypeface(countryTextView.getTypeface(), Typeface.ITALIC);
            }
        });
        puzzleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.getAllPlayersPuzzle().observe(MainActivity.this, new Observer<List<Player>>() {
                    @Override
                    public void onChanged(List<Player> players) {
                        itemAdapter = new ItemAdapter(MainActivity.this, players);
                        playerListView.setAdapter(itemAdapter);
                        if (players.size() == 0) {
                            noPlayersTextView.setVisibility(View.VISIBLE);
                        } else noPlayersTextView.setVisibility(View.INVISIBLE);
                    }
                });
                rapidTextView.setTypeface(rapidTextView.getTypeface(), Typeface.ITALIC);
                blitzTextView.setTypeface(blitzTextView.getTypeface(), Typeface.ITALIC);
                bulletTextView.setTypeface(bulletTextView.getTypeface(), Typeface.ITALIC);
                puzzleTextView.setTypeface(puzzleTextView.getTypeface(), Typeface.BOLD_ITALIC);
                countryTextView.setTypeface(countryTextView.getTypeface(), Typeface.ITALIC);
            }
        });

        countryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.getAllPlayersCountry().observe(MainActivity.this, new Observer<List<Player>>() {
                    @Override
                    public void onChanged(List<Player> players) {
                        itemAdapter = new ItemAdapter(MainActivity.this, players);
                        playerListView.setAdapter(itemAdapter);
                        if (players.size() == 0) {
                            noPlayersTextView.setVisibility(View.VISIBLE);
                        } else noPlayersTextView.setVisibility(View.INVISIBLE);
                    }
                });
                rapidTextView.setTypeface(rapidTextView.getTypeface(), Typeface.ITALIC);
                blitzTextView.setTypeface(blitzTextView.getTypeface(), Typeface.ITALIC);
                bulletTextView.setTypeface(bulletTextView.getTypeface(), Typeface.ITALIC);
                puzzleTextView.setTypeface(puzzleTextView.getTypeface(), Typeface.ITALIC);
                countryTextView.setTypeface(countryTextView.getTypeface(), Typeface.BOLD_ITALIC);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchValue = nameEditText.getText().toString();
                String url = apiString + searchValue;
                nameEditText.getText().clear();
                errorTextView.setText("");

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url + "/stats", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response.has("chess_rapid")) {
                                        JSONObject rapid = response.getJSONObject("chess_rapid");
                                        JSONObject rapidLast = rapid.getJSONObject("last");
                                        int rapidRating = rapidLast.getInt("rating");
                                        goToViewPlayer.putExtra("rapidRating", rapidRating);
                                    } else goToViewPlayer.putExtra("rapidRating", 0);

                                    if (response.has("chess_blitz")) {
                                        JSONObject blitz = response.getJSONObject("chess_blitz");
                                        JSONObject blitzLast = blitz.getJSONObject("last");
                                        int blitzRating = blitzLast.getInt("rating");
                                        goToViewPlayer.putExtra("blitzRating", blitzRating);
                                    } else goToViewPlayer.putExtra("blitzRating", 0);

                                    if (response.has("chess_bullet")) {
                                        JSONObject bullet = response.getJSONObject("chess_bullet");
                                        JSONObject bulletLast = bullet.getJSONObject("last");
                                        int bulletRating = bulletLast.getInt("rating");
                                        goToViewPlayer.putExtra("bulletRating", bulletRating);
                                    } else goToViewPlayer.putExtra("bulletRating", 0);

                                    if (response.has("tactics")) {
                                        JSONObject puzzle = response.getJSONObject("tactics");
                                        JSONObject puzzleHighest = puzzle.getJSONObject("highest");
                                        int puzzleRating = puzzleHighest.getInt("rating");
                                        goToViewPlayer.putExtra("puzzleRating", puzzleRating);
                                    } else {
                                        goToViewPlayer.putExtra("tactics", 0);
                                    }
                                    startActivityForResult(goToViewPlayer, VIEW_PLAYER_ACTIVITY_REQUEST_CODE);
                                } catch (JSONException e) {
                                    errorTextView.setText("Name does not exist..");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorTextView.setText(error.toString());
                    }
                });
                JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response.has("username")) {
                                        goToViewPlayer.putExtra("playerName", response.getString("username"));
                                    } else goToViewPlayer.putExtra("playerName", "Unknown user");
                                    if (response.has("avatar")) {
                                        goToViewPlayer.putExtra("imageURL",  response.getString("avatar"));
                                    } else goToViewPlayer.putExtra("imageURL", "https://img.freepik.com/free-vector/question-mark-sign-brush-stroke-trash-style-typography-vector_53876-140880.jpg?w=826&t=st=1720621193~exp=1720621793~hmac=db83fc1079c4b53c6d40c9c737522557df08af3b187bff2210a527f7749bdc65.png");
                                    if (response.has("country")) {
                                        goToViewPlayer.putExtra("country", response.getString("country").substring(34));
                                    } else goToViewPlayer.putExtra("country",  "Country not declared");
                                    mQueue.add(request);

                                } catch (JSONException e) {
                                    errorTextView.setText(e.toString());
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorTextView.setText(error.toString());
                    }
                });
                mQueue.add(request2);
            }
        });

        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Player thisPlayer = (Player) playerListView.getAdapter().getItem(i);
                goToViewPlayer.putExtra("id", thisPlayer.getPlayerId());
                startActivityForResult(goToViewPlayer, VIEW_PLAYER_ACTIVITY_REQUEST_CODE);
            }
        });
    }
}