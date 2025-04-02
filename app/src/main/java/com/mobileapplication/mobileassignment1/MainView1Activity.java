package com.mobileapplication.mobileassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainView1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // If your layout file is mainview1.xml, reference it here:
        setContentView(R.layout.mainview1);

        TextView ivBackArrow = findViewById(R.id.footer);
        ivBackArrow.setOnClickListener(view -> {
            // Go back or finish this activity
            finish();
        });

        Button btnMore = findViewById(R.id.btnMore);
        HorizontalScrollView horizontalScrollView = findViewById(R.id.horizontalScrollView);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainView1Activity", "btnMore clicked. Current HSV visibility: " + horizontalScrollView.getVisibility());
                if (horizontalScrollView.getVisibility() == View.INVISIBLE) {
                    // Show the scroll view
                    horizontalScrollView.setVisibility(View.VISIBLE);
                } else {
                    // Hide it again
                    horizontalScrollView.setVisibility(View.INVISIBLE);
                }
            }
        });


// Assuming you have these instance variables (or define them in onCreate):
        Button btnNaruto = findViewById(R.id.naruto);
        Button btnSakura = findViewById(R.id.sakura);
        Button btnSasuke = findViewById(R.id.sasuke);

        ImageView imgNaruto = findViewById(R.id.imgNaruto);
        ImageView imgSakura = findViewById(R.id.imgSakura);
        ImageView imgSasuke = findViewById(R.id.imgSasuke);

// Naruto button
        btnNaruto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If Naruto is already visible, hide it; otherwise, hide all and show Naruto.
                if (imgNaruto.getVisibility() == View.VISIBLE) {
                    imgNaruto.setVisibility(View.INVISIBLE);
                } else {
                    imgNaruto.setVisibility(View.VISIBLE);
                    imgSakura.setVisibility(View.INVISIBLE);
                    imgSasuke.setVisibility(View.INVISIBLE);
                }
            }
        });

// Sakura button
        btnSakura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgSakura.getVisibility() == View.VISIBLE) {
                    imgSakura.setVisibility(View.INVISIBLE);
                } else {
                    imgNaruto.setVisibility(View.INVISIBLE);
                    imgSakura.setVisibility(View.VISIBLE);
                    imgSasuke.setVisibility(View.INVISIBLE);
                }
            }
        });

// Sasuke button
        btnSasuke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgSasuke.getVisibility() == View.VISIBLE) {
                    imgSasuke.setVisibility(View.INVISIBLE);
                } else {
                    imgNaruto.setVisibility(View.INVISIBLE);
                    imgSakura.setVisibility(View.INVISIBLE);
                    imgSasuke.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}

