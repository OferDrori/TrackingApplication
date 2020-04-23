package com.example.trackingapplication;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static com.example.trackingapplication.Keys.KEY_TRACKER;

public class TrackerMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MySharedPreferences msp;
    private Tracker tracker;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("User");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        msp=new MySharedPreferences(this);
        Gson gson= new Gson();
        tracker = gson.fromJson(msp.getString(KEY_TRACKER,""),new TypeToken<Tracker>() {
        }.getType());
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
       // changeMap();
        FirebaseUser userUID = FirebaseAuth.getInstance().getCurrentUser();
        myRef.child(userUID.getUid()).child("Trackers").child(tracker.getPhoneNumber()).addValueEventListener(new ValueEventListener() {
            @Override
            // Add Trackers to the list
            public void onDataChange(DataSnapshot dataSnapshot) {
                    tracker = dataSnapshot.getValue(Tracker.class);
                    changeMap();

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }
    void changeMap(){
        mMap.clear();
        LatLng location = new LatLng(tracker.getLatitude(), tracker.getLongitude());
        mMap.addMarker(new MarkerOptions().position(location).title(tracker.getTrackerName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

    }
}
