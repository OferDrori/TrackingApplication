package com.example.trackingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.example.trackingapplication.Keys.KEY_TRACKER;

public class ImagesListActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    MySharedPreferences msp;
    private ArrayList<String> listOfImagesInStringFormat=new ArrayList<>();
    private ImageAdapter listOfImagesInStringFormatAdapter;
    private ListView listViewOfImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_list);
        msp=new MySharedPreferences(this);
        Gson gson= new Gson();
       Tracker tracker = gson.fromJson(msp.getString(KEY_TRACKER,""),new TypeToken<Tracker>() {
        }.getType());
        String phoneNumber=tracker.getPhoneNumber();
        Log.i("phoneNumber",phoneNumber);
        listViewOfImages=findViewById(R.id.ListOfImagesListActivity_ListView);
        DatabaseReference myRef = database.getReference("User");
        listOfImagesInStringFormatAdapter=new ImageAdapter(this,listOfImagesInStringFormat);
        listViewOfImages.setAdapter(listOfImagesInStringFormatAdapter);
        myRef.child(phoneNumber).child("photo").addValueEventListener(new ValueEventListener() {
            @Override
            // Add Trackers to the list
            public void onDataChange(DataSnapshot dataSnapshot) {
                listOfImagesInStringFormat.clear();//Not necessary just for now in the end we just need to add the end of the array
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String photo = ds.getValue(String.class);
                    listOfImagesInStringFormat.add(photo);
                    Log.i("phoneNumber",photo);

                }
                listViewOfImages.setAdapter(listOfImagesInStringFormatAdapter);//check if its necessary
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }
}