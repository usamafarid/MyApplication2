package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class register extends AppCompatActivity {
    private EditText username;
    private EditText email;
    private EditText password;
    private Button register;
    private TextView userlogin;
    private  DatabaseReference mRootRef;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);


         mRootRef = FirebaseDatabase.getInstance().getReference();

        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this, login.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username=username.getText().toString();
                String txt_email=email.getText().toString();
                String txt_password=password.getText().toString();
                String txt_register=register.getText().toString();

                if(TextUtils.isEmpty(txt_username)||TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)||TextUtils.isEmpty(txt_register)){
                    Toast.makeText(register.this,"empty",Toast.LENGTH_SHORT).show();
                }
                else if(txt_password.length()<6 ){
                  Toast.makeText(register.this,"PASSword too short",Toast.LENGTH_SHORT).show();

                }
                else {
                    registeruser(txt_username,txt_password,txt_email);
                }
            }
        });
    }

    private void registeruser(String Username, String Password, String Email) {
    mauth.createUserWithEmailAndPassword(Email,Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
        @Override
        public void onSuccess(AuthResult authResult) {
            HashMap<String,String> map=new HashMap<>();
            map.put("name", Username);
            map.put("email",Email);
            map.put("password",Password);
            map.put("id",mauth.getCurrentUser().getUid());
            mRootRef.child("users").child(mauth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(register.this,"upload profile",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(register.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }
}