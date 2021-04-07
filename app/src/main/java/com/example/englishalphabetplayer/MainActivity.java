package com.example.englishalphabetplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Updating List View
        String []alphabets = {"A", "B", "C", "D", "E", "F", "G","H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int[] alphabetStarter = new int[]{14, 21, 32, 39, 48, 56, 65, 73, 85, 89, 100, 107, 116, 122, 132, 138, 151, 158, 167, 173, 183, 189, 199, 206, 215, 221};
        int[] alphabetsResources = {R.raw.a, R.raw.b, R.raw.c, R.raw.d, R.raw.e, R.raw.f, R.raw.g, R.raw.h, R.raw.i, R.raw.j, R.raw.k, R.raw.l, R.raw.m, R.raw.n, R.raw.o, R.raw.p, R.raw.q, R.raw.r, R.raw.s, R.raw.t, R.raw.u, R.raw.v, R.raw.w,R.raw.x, R.raw.y, R.raw.z};

        // https://abhiandroid.com/ui/listview
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), alphabetsResources);
        // Getting List View
        listView = findViewById(R.id.listView);
        listView.setAdapter(customAdapter);

        // Add Event Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Switch to new activity
                Intent intent = new Intent(MainActivity.this, AlphabetAnimation.class);
                intent.putExtra("Alphabet", alphabets[position]);
                intent.putExtra("Starter", alphabetStarter[position]);
                startActivity(intent);
            }
        });
    }
}