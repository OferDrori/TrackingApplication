package com.example.trackingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfTrackersActivity extends AppCompatActivity {
    private ListView listViewOfTrackers;
    private TextView addNewTrackerTextView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("User");
    private FirebaseAuth mAuth;
    private ArrayList<Tracker> arrayListOfTrackers = new ArrayList<>();
    private TrackerAdapter trackerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_trackers);
        listViewOfTrackers = findViewById(R.id.ListOfTrackersActivity_ListView);
        addNewTrackerTextView = findViewById(R.id.add_new_tracker_text_view);
        addNewTrackerTextView.setOnClickListener(addNewTrackerFunc);
        trackerAdapter=new TrackerAdapter(this,arrayListOfTrackers);
        listViewOfTrackers.setAdapter(trackerAdapter);
        FirebaseUser userUID = FirebaseAuth.getInstance().getCurrentUser();

        myRef.child(userUID.getUid()).child("Trackers").addValueEventListener(new ValueEventListener() {
            @Override
            // Add Trackers to the list
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Tracker tracker = ds.getValue(Tracker.class);
                    arrayListOfTrackers.add(tracker);
                }
                listViewOfTrackers.setAdapter(trackerAdapter);//check if its necessary


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }


    private void goToNextActivity(Class activity)
    {
        Intent intent = new Intent(this , activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }


    View.OnClickListener addNewTrackerFunc = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goToNextActivity(AddTrackerActivity.class);
        }

    };




}
