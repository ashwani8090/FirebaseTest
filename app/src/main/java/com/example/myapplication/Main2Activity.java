package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {

    EditText email,password;
    Button button;

    String Email,Password;
    FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;
    int value;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        button=findViewById(R.id.button);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        value=sharedPreferences.getInt("key",1);




/*
        firebaseAuth.onAuthStateChanged(function(getCuuser) {
            if (user) {
                // User is signed in.
            } else {
                // No user is signed in.
            }
        });

  */













    if (value==1) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = findViewById(R.id.email);
                password = findViewById(R.id.password);
                Email = email.getText().toString().trim();
                Password = password.getText().toString().trim();


                UserLogin(Email, Password);


            }
        });


    }

    else{

        startActivity(new Intent(Main2Activity.this,logout.class));


    }







    }

    private void UserLogin(String email, String password) {

        firebaseAuth=FirebaseAuth.getInstance();
       firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()){

                    startActivity(new Intent(Main2Activity.this,logout.class));

                }
                else
                {
                    Toast.makeText(Main2Activity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                }






           }
       });






    }


}
