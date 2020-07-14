package com.example.trackingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OptionsToDo extends AppCompatActivity {

    TextView goToMapTextView;
    TextView goToImagesTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_to_do);
        goToMapTextView=findViewById(R.id.textView_view_his_location);
        goToImagesTextView=findViewById(R.id.textView_view_his_images);
        goToMapTextView.setOnClickListener(goToMap);
        goToImagesTextView.setOnClickListener(goToImagesList);
    }
    View.OnClickListener goToMap = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goToNextActivity(TrackerMapActivity.class);
        }

    };
    View.OnClickListener goToImagesList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goToNextActivity(ImagesListActivity.class);
        }

    };
    private void goToNextActivity(Class activity)
    {
        Intent intent = new Intent(this , activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
}