package com.mobileapplication.mobileassignment1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class ControlActivity extends AppCompatActivity {

    View joystickKnob;
    ImageView objectToMove;
    FrameLayout joystickBase;

    float baseCenterX, baseCenterY;
    float maxRadius;

    View zoomKnob;
    FrameLayout zoomBase;

    float zoomCenterY;
    float zoomMaxMove;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        // Setting Back Button
        ImageButton BackButton = findViewById(R.id.BackButton);
        BackButton.setOnClickListener(v -> {
            Intent intent = new Intent(ControlActivity.this, MainActivity.class);       // ve lai trang truoc
            startActivity(intent);
        });

        // Use when finish the back page?
//        ImageButton backButton = findViewById(R.id.BackButton);
//        backButton.setOnClickListener(v -> {
//            finish();
//        });

        // Set Camera Button
        ImageButton cameraButton = findViewById(R.id.CameraSwitchButton);

        cameraButton.setOnClickListener(v -> {
            Intent intent = new Intent(ControlActivity.this, CameraModeActivity.class);
            startActivity(intent);
        });

        joystickKnob = findViewById(R.id.joystickKnob);
        joystickBase = findViewById(R.id.joystickBase);
        objectToMove = findViewById(R.id.objectToMove);

        // Lấy thông số joystick sau khi layout hoàn thành
        joystickBase.post(() -> {
            baseCenterX = joystickBase.getX() + joystickBase.getWidth() / 2f;
            baseCenterY = joystickBase.getY() + joystickBase.getHeight() / 2f;
            maxRadius = joystickBase.getWidth() / 2f - joystickKnob.getWidth() / 2f;
        });

        zoomKnob = findViewById(R.id.zoomKnob);
        zoomBase = findViewById(R.id.zoomBase);

        zoomBase.post(() -> {
            zoomCenterY = zoomBase.getY() + zoomBase.getHeight() / 2f;
            zoomMaxMove = zoomBase.getHeight() / 2f - zoomKnob.getHeight() / 2f;
        });

        setupJoystick();
        setupZoom();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupZoom() {
        zoomKnob.setOnTouchListener((view, event) -> {
            float rawY = event.getRawY();
            float dy = rawY - zoomCenterY;

            // Giới hạn di chuyển trong phạm vi zoomBase
            if (Math.abs(dy) > zoomMaxMove) {
                dy = zoomMaxMove * Math.signum(dy);
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    float knobY = zoomBase.getHeight() / 2f - zoomKnob.getHeight() / 2f + dy;
                    zoomKnob.setY(knobY);

                    float minScale = 0.5f;
                    float maxScale = 8.0f;

                    float t = 1 - ((dy + zoomMaxMove) / (2 * zoomMaxMove)); // Chuẩn hóa về 0 - 1
                    float scale = minScale + (maxScale - minScale) * t;

                    objectToMove.setScaleX(scale);
                    objectToMove.setScaleY(scale);
                    break;

            }
            return true;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupJoystick() {
        joystickKnob.setOnTouchListener((view, event) -> {
            float dx = event.getRawX() - baseCenterX;
            float dy = event.getRawY() - baseCenterY;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    if (distance < maxRadius) {
                        joystickKnob.setX(joystickBase.getWidth() / 2f - joystickKnob.getWidth() / 2f + dx);
                        joystickKnob.setY(joystickBase.getHeight() / 2f - joystickKnob.getHeight() / 2f + dy);
                    } else {
                        float ratio = maxRadius / distance;
                        dx *= ratio;
                        dy *= ratio;
                        joystickKnob.setX(joystickBase.getWidth() / 2f - joystickKnob.getWidth() / 2f + dx);
                        joystickKnob.setY(joystickBase.getHeight() / 2f - joystickKnob.getHeight() / 2f + dy);
                    }

                    // Di chuyển vật thể
                    objectToMove.setTranslationX(dx * 2);  // *2 để tăng tốc
                    objectToMove.setTranslationY(dy * 2);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    joystickKnob.setX(joystickBase.getWidth() / 2f - joystickKnob.getWidth() / 2f);
                    joystickKnob.setY(joystickBase.getHeight() / 2f - joystickKnob.getHeight() / 2f);
                    objectToMove.setTranslationX(0);
                    objectToMove.setTranslationY(0);
                    break;
            }
            return true;
        });
    }
}
