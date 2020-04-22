package com.example.trackingapplication.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.trackingapplication.R;
import com.example.trackingapplication.User;
import com.example.trackingapplication.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActicity extends AppCompatActivity {
    private EditText fullNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acticity);
        fullNameEditText=findViewById(R.id.fullName_edittext_registration_activity);
        emailEditText=findViewById(R.id.email_registration_edt);
        passwordEditText=findViewById(R.id.editText_password_activity_regitration);
        registerButton=findViewById(R.id.create_btn_activity_regitration);
        registerButton.setOnClickListener(goToMainPage);
        mAuth = FirebaseAuth.getInstance();
        // Write a message to the database




    }

    View.OnClickListener goToMainPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            registerFunc(view);

        }

    };

    private void goToNextActivity(Class activity)
    {
        Intent intent = new Intent(this , activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }


    private void registerFunc(View view) {
        mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(fullNameEditText.getText().toString(), emailEditText.getText().toString(),
                                    passwordEditText.getText().toString());
                            FirebaseUser userUID = FirebaseAuth.getInstance().getCurrentUser();
                            myRef.child(userUID.getUid()).setValue(user);
                            goToNextActivity(LoginActivity.class);

                        } else {

                        }

                        // ...
                    }
                });
    }

}
