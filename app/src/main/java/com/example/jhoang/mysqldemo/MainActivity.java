package com.example.jhoang.mysqldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText UsernameEt, PasswordEt;
    private static Button btnAddTeacher;
    NotificationCompat.Builder notification;
    private static final int EmarchingID = 174901;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        UsernameEt = (EditText)findViewById(R.id.etUsername);
        PasswordEt = (EditText)findViewById(R.id.etPassword);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        OnClickButtonListener();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void OnClickButtonListener() {
        btnAddTeacher = (Button) findViewById(R.id.btnAddTeacher);
        btnAddTeacher.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, AddTeacher.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnLogin(View view){
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

    /*notification.setSmallIcon(R.drawable.ic_queue_music_black_24dp);
    notification.setTicker("This is the Ticker");
    notification.setWhen(System.currentTimeMillis());
    notification.setContentTitle("Here is the title");
    notification.setContentText("notification message here");

    Intent NotifyIntent = new Intent(this, MainActivity.class);
    PendingIntent pendingIntent= PendingIntent.getActivity(this, 0, NotifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    notification.setContentIntent(pendingIntent);
    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    nm.notify(EmarchingID, notification.build());*/

}
