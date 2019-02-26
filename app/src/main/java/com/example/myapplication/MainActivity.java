package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


   private EditText editTextName,editTextEmail,editTextPassword,editTextPhone;
    private FirebaseAuth mAuth;
    private  Userinfo userinfo;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

      //  Intent i=new Intent(MainActivity.this,Main2Activity.class);
        //startActivity(i);



        editTextEmail=findViewById(R.id.editText);

        editTextName=findViewById(R.id.editText2);
        editTextPassword=findViewById(R.id.editText3);
        editTextPhone=findViewById(R.id.editText4);

        progressBar=findViewById(R.id.progressBar);









    }
    @Override

    protected void onStart() {

        super.onStart();



        if (mAuth.getCurrentUser() != null) {

            //handle the already login user

        }

    }




    private void registerUser() {

        final String name = editTextName.getText().toString().trim();

        final String email = editTextEmail.getText().toString().trim();

        final String password = editTextPassword.getText().toString().trim();

        final String phone = editTextPhone.getText().toString().trim();



        if (name.isEmpty()) {

            editTextName.setError(getString(R.string.input_error_name));

            editTextName.requestFocus();

            return;

        }



        if (email.isEmpty()) {

            editTextEmail.setError(getString(R.string.input_error_email));

            editTextEmail.requestFocus();

            return;

        }




        if (password.isEmpty()) {

            editTextPassword.setError(getString(R.string.input_error_password));

            editTextPassword.requestFocus();

            return;

        }













        progressBar.setVisibility(View.VISIBLE);













        mAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {



                        if (task.isSuccessful()) {



                            final Userinfo userinfo = new Userinfo(

                                   email,
                                    password,
                                    name,
                                    phone

                            );









                            FirebaseDatabase.getInstance().getReference("Customer").child(FirebaseAuth.getInstance().
                                    getCurrentUser().getUid()).setValue(userinfo).addOnCompleteListener(new OnCompleteListener<Void>() {





                                @Override

                                public void onComplete(@NonNull Task<Void> task) {



                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {

                                        Toast.makeText(MainActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();

                                    } else {

                                        //display a failure message

                                    }

                                }

                            });



                        } else {

                            Toast.makeText(MainActivity.this, task.getException().
                                    getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                });



    }


    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.button:

                registerUser();

                break;

        }
}














}
