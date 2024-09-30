package com.example.chessratingcomparison;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PlayerNotesActivity extends AppCompatActivity {
    PlayerDao dao;
    NoteItemAdapter itemAdapter;
    long playerId;
    String playerName;
    ListView notesListView;
    EditText noteTitleEditText;
    EditText noteTextEditText;
    Button addNoteButton;
    ImageButton deleteImageButton;
    TextView noNotesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_notes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        notesListView = findViewById(R.id.notesListView);
        noteTextEditText = findViewById(R.id.noteTextEditText);
        noteTitleEditText = findViewById(R.id.noteTitleEditText);
        addNoteButton = findViewById(R.id.addNoteButton);
        deleteImageButton = notesListView.findViewById(R.id.deleteImageButton);
        noNotesTextView = findViewById(R.id.noNotesTextView);

        AppDatabase db = AppDatabase.getInstance(this);
        dao = db.playerDao();

        playerId = getIntent().getExtras().getLong("playerId");
        playerName = getIntent().getExtras().getString("playerName");

        getSupportActionBar().setTitle("Notes: " + playerName);

        dao.getAllNotes(playerId).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                itemAdapter = new NoteItemAdapter(PlayerNotesActivity.this, notes, dao);
                notesListView.setAdapter(itemAdapter);
                if (notes.size() == 0) {
                    noNotesTextView.setVisibility(View.VISIBLE);
                } else noNotesTextView.setVisibility(View.INVISIBLE);
            }
        });
        
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = noteTitleEditText.getText().toString();
                if (title == "") {
                    Toast.makeText(PlayerNotesActivity.this, "Please create a title", Toast.LENGTH_SHORT).show();
                    return;
                }
                String text = noteTextEditText.getText().toString();
                if (text == "") {
                    Toast.makeText(PlayerNotesActivity.this, "Please write context to the note", Toast.LENGTH_SHORT).show();
                    return;
                }
                noteTitleEditText.getText().clear();
                noteTextEditText.getText().clear();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dao.addNote(new Note(title, text, playerId));
                    }
                }).start();
                itemAdapter.notifyDataSetChanged();
            }
        });
    }
}