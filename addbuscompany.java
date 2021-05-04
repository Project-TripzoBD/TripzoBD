package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addbuscompany extends AppCompatActivity {
    EditText companytext;
    DatabaseReference databaseReference;
    String text ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbuscompany);

        companytext = (EditText) findViewById(R.id.entercompamy);
        databaseReference = FirebaseDatabase.getInstance().getReference("Bus company name");
    }

    public void btnclick(View view) {
        text = companytext.getText().toString().trim();
        databaseReference.push().setValue(text);
        companytext.setText("");
        Toast.makeText(addbuscompany.this,"Data submitted successfully", Toast.LENGTH_SHORT).show();
    }
}