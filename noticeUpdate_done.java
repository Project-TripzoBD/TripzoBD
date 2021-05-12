package com.example.adminpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class noticeUpdate extends AppCompatActivity {

    String UserToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_update);

        FirebaseMessaging.getInstance().subscribeToTopic("all");

        // fcm settings for perticular user

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            UserToken = Objects.requireNonNull(task.getResult()).getToken();
                            Log.d("taaa","token"+UserToken);

                         }

                    }
                });



        EditText  title =findViewById(R.id.update1);
        EditText message = findViewById(R.id.update2);
        EditText token = findViewById(R.id.update3);

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

        findViewById(R.id.sentBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !message.getText().toString().isEmpty() &&
                        !token.getText().toString().isEmpty()) {

                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender(token.getText().toString(), title.getText().toString(),
                            message.getText().toString(), getApplicationContext(), noticeUpdate.this);

                    notificationsSender.SendNotifications();

                } else {

                    Toast.makeText(noticeUpdate.this, "Enter Details", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}