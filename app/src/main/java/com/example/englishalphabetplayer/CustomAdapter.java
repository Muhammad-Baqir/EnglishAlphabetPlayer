package com.example.englishalphabetplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int alphabets[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, int[] alphabets) {
        this.context = context;
        this.alphabets = alphabets;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return alphabets.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(alphabets[i]);
        return view;
    }
}