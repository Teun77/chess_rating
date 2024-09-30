package com.example.chessratingcomparison;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<Player> players;

    public ItemAdapter(Context c, List<Player> players){
        this.players = players;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int i) {
        return players.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.activity_custom_listview, null);
        TextView nameTextView = v.findViewById(R.id.nameTextView);
        TextView rapidTextView = v.findViewById(R.id.rapidTextView);
        TextView blitzTextView = v.findViewById(R.id.blitzTextView);
        TextView bulletTextView = v.findViewById(R.id.bulletTextView);
        TextView puzzleTextView = v.findViewById(R.id.puzzleTextView);
        TextView countryTextView = v.findViewById(R.id.countryTextView);
        ImageView avatarImageView = v.findViewById(R.id.avatarImageView);

        String name = players.get(i).getName();
        int rapid = players.get(i).getRapidRating();
        int blitz = players.get(i).getBlitzRating();
        int bullet = players.get(i).getBulletRating();
        int puzzle = players.get(i).getPuzzleRating();
        String country = players.get(i).getCountry();
        String imageURL = players.get(i).getImageURL();

        nameTextView.setText(name);
        rapidTextView.setText(String.valueOf(rapid));
        blitzTextView.setText(String.valueOf(blitz));
        bulletTextView.setText(String.valueOf(bullet));
        puzzleTextView.setText(String.valueOf(puzzle));
        countryTextView.setText(country);

        if (imageURL != null) {
            Picasso.get().load(imageURL).into(avatarImageView);
        }

        return v;
    }
}
