package com.example.trackingapplication.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trackingapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginBtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameEditText=findViewById(R.id.name_login_editText);
        passwordEditText=findViewById(R.id.pass_login_editText);
        loginBtn=findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(goToMainPage);
        mAuth = FirebaseAuth.getInstance();
    }

    private void goToNextActivity(Class activity)
    {
        Intent intent = new Intent(this , activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    View.OnClickListener goToMainPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (userNameEditText.getText().toString().equals(""))
                goToNextActivity(RegisterActicity.class);
            else
                loginFunc(view);

        }
    };



    void loginFunc(View view){
        mAuth.signInWithEmailAndPassword(userNameEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // go to main

                            Context context = getApplicationContext();
                            CharSequence text = "success!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            Log.d("pppt","success login");

                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {

                            Context context = getApplicationContext();
                            CharSequence text = "failed!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            Log.d("pppt","failed login");


                        }

                        // ...
                    }
                });

    }





}
