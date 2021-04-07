package com.example.englishalphabetplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.TimedMetaData;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Dictionary;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AlphabetAnimation extends AppCompatActivity {
    TextView alphabetTextView;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    MediaPlayer mediaPlayer;
    int alphabetStarter;
    final int HALF_DURATION = 3500;
    Timer timer;
    DisplayMetrics displayMetrics; // https://stackoverflow.com/questions/4743116/get-screen-width-and-height-in-android

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_animation);

        // Alphabet String
        String alphabet = getIntent().getStringExtra("Alphabet");
        alphabetStarter = getIntent().getIntExtra("Starter",  0);

        // Change Text View
        alphabetTextView  = findViewById(R.id.textViewAlphabtet);
        alphabetTextView.setText("Alphabet "  + alphabet);

        // Set Image View
        imageView = findViewById(R.id.alphabetView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        // Set Sources
        imageView.setImageResource(getResId(alphabet.toLowerCase(), R.raw.class));
        imageView1.setImageResource(getResId(alphabet.toLowerCase() + "img1", R.raw.class));
        imageView2.setImageResource(getResId(alphabet.toLowerCase() + "img2", R.raw.class));

        // Display Metrics
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        // Start Player and Animation
        StartPlayer();
        StartAnimation();
    }

    private void StartAnimation() {
        // Stop
        int width = (displayMetrics.widthPixels)/2;
        // Initial Location
        imageView.setX(-width);
        imageView1.setX(width);
        imageView2.setX(-width);
        // Alphabet Animation
        imageView.animate().setDuration(HALF_DURATION*2).translationX(width);
        imageView1.animate().rotation(720).translationX(-width).setDuration(HALF_DURATION*2);
        imageView2.animate().rotation(720).translationX(width).setDuration(HALF_DURATION*2);
    }

    private void StartPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        mediaPlayer.seekTo(alphabetStarter * 1000);
        mediaPlayer.start();

        // Stop Media Player
        AutomaticallyStop();
    }

    // https://stackoverflow.com/questions/11550789/android-simple-how-to-stop-mediaplayer-after-given-time
    private void AutomaticallyStop() {
        // Stop After 7 sec
        timer = new Timer("timer", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mediaPlayer.stop();
            }
        }, 7000); // 7sec
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    public void Replay(View view) {
        // Stop player & animation
        timer.cancel();
        mediaPlayer.stop();

        StartPlayer();
        StartAnimation();
    }

    // https://stackoverflow.com/questions/4427608/android-getting-resource-id-from-string
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}