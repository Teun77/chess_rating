package com.example.chessratingcomparison;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class NoteItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<Note> notes;
    PlayerDao dao;

    public NoteItemAdapter(Context c, List<Note> notes, PlayerDao dao){
        this.notes = notes;
        this.dao = dao;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.note_custom_listview, null);
        TextView titleTextView = v.findViewById(R.id.noteTitleTextView);
        TextView textTextView = v.findViewById(R.id.noteTextTextView);
        ImageButton deleteImageButton = v.findViewById(R.id.deleteImageButton);

        titleTextView.setText(notes.get(i).title);
        textTextView.setText(notes.get(i).text);

        deleteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dao.deleteNote(notes.get(i).noteId);
                    }
                }).start();
                notifyDataSetChanged();
            }
        });

        return v;
    }
}
