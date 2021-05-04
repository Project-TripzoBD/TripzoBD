package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button addbuscompanybtn,connectList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addbuscompanybtn = (Button) findViewById(R.id.addbuscompany);
        connectList = (Button) findViewById(R.id.list);


        addbuscompanybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addbuscompany.class);
                startActivity(intent);
            }
        });

        connectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this,viewbuslist.class);
                startActivity(intent);
            }
        });


    }


}