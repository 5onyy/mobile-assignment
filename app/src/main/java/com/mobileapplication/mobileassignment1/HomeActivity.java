package com.mobileapplication.mobileassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);  // Create a corresponding layout file (activity_home.xml)

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainView1Activity.class);
                startActivity(intent);
            }
        });

        // Similarly, navigate to another activity for button2
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For example, if you have MainView2Activity:
                Intent intent = new Intent(HomeActivity.this, MainView2Activity.class);
                startActivity(intent);
            }
        });
    }
}
