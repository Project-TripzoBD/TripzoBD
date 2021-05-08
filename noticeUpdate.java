package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.messaging.FirebaseMessaging;

public class noticeUpdate extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_update);

        FirebaseMessaging.getInstance().subscribeToTopic("all");


       EditText  title =findViewById(R.id.update1);
        EditText message = findViewById(R.id.update2);

        findViewById(R.id.sentBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (!title.getText().toString().isEmpty() && !message.getText().toString().isEmpty()){
                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all",title.getText().toString(),
                            message.getText().toString(),getApplicationContext(),noticeUpdate.this);

                    notificationsSender.SendNotifications();



                }else {
                    Toast.makeText(noticeUpdate.this,"Enter Details!", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }


}