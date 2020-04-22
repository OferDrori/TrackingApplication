package com.example.trackingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrackerActivity extends AppCompatActivity {
    private EditText addTrackerNameEditText;
    private Button createTrackerBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Tracker");
    private FirebaseAuth mAuth;
    FirebaseUser userUID = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tracker);
        addTrackerNameEditText=findViewById(R.id.addTracker_TrackerName_editText);
        createTrackerBtn=findViewById(R.id.addTracker_btn_create);
        mAuth = FirebaseAuth.getInstance();
        createTrackerBtn.setOnClickListener(addTracker);
    }

    View.OnClickListener addTracker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!(addTrackerNameEditText.getText().toString().equals(""))) {
                myRef.child(userUID.getUid()).child(addTrackerNameEditText.getText().toString()).setValue("Todo Location argument ");
                openWhatsApp(v,"+972506332027");
             //   Intent intent = new Intent(CreateNewGroup.this, GroupsScreen.class);
               // startActivity(intent);
                finish();
            }

        }
    };

        public void openWhatsApp(View view,String toNumber){
            //toNumber- Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
        try {
            String text = "you Need to add this path to get people to follow you-"+ userUID.getUid()+"and your track  "+addTrackerNameEditText.getText().toString();// Replace with your message.
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    }

