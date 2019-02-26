package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class logout extends AppCompatActivity {

    Button logout;
    FirebaseAuth firebaseAuth;
    int value;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);


        logout=findViewById(R.id.logout);
        value=0;
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit=sharedPreferences.edit();
        edit.putInt("key",value);
        edit.apply();

    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            firebaseAuth=FirebaseAuth.getInstance();


            value=0;
            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor edit=sharedPreferences.edit();
            edit.putInt("key",value);
            edit.apply();

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(logout.this,Main3Activity.class));


        }
    });








    }
}
