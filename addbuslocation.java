package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addbuslocation extends AppCompatActivity {
    EditText bus;
    DatabaseReference databaseReference;
    String text ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbuslocation);

        bus = (EditText) findViewById(R.id.busss);
        databaseReference = FirebaseDatabase.getInstance().getReference("Bus Location");
    }

    public void btnclick(View view) {

        text = bus.getText().toString().trim();
        databaseReference.push().setValue(text);

        bus.setText("");
        Toast.makeText(addbuslocation.this,"Data submitted successfully", Toast.LENGTH_SHORT).show();

    }
}