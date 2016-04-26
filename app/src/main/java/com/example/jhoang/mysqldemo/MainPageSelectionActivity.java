package com.example.jhoang.mysqldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainPageSelectionActivity extends AppCompatActivity {
    private static Button btnMusic;
    private static Button btnCoordinate;
    private static Button btneditmusicbook;
    private static Button btneditmusicmvt;
    private static Button btneditcoordinatebook;
    private static Button btneditcoordinatesheet;
    private static Button btnMusicBook;
    private static Button btnCoordinateBook;

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_selection);

        Intent extraIntent = getIntent();
        username = extraIntent.getStringExtra("username");
        password = extraIntent.getStringExtra("password");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        OnClickButtonListener();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void OnClickButtonListener() {
        btnMusic = (Button)findViewById(R.id.btnMusic);
        btnCoordinate = (Button)findViewById(R.id.btnCoordinate);
        btneditmusicbook = (Button)findViewById(R.id.button_editmusicbook);
        btneditmusicmvt = (Button)findViewById(R.id.button_editmusicmvt);
        btneditcoordinatebook = (Button)findViewById(R.id.button_editcoordinatebook);
        btneditcoordinatesheet =(Button)findViewById(R.id.button_editcoordinatesheet);
        btnMusicBook = (Button)findViewById(R.id.button_musicbook);
        btnCoordinateBook = (Button)findViewById(R.id.button_coordinatebook);
        btnMusic.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.jhoang.mysqldemo.MusicSelectionActivity");
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    }
                }
        );
        btnCoordinate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.jhoang.mysqldemo.CoordinateSelectionActivity");
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    }
                }
        );


        btneditmusicbook.setOnClickListener(
                new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Intent intent = new Intent("com.example.jhoang.mysqldemo.Music_BookActivity");
                            startActivity(intent);
                        }
                    }
            );
        btneditmusicmvt.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.jhoang.mysqldemo.Music_MvtActivity");
                        startActivity(intent);
                    }
                }
        );

        btneditcoordinatebook.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.jhoang.mysqldemo.Coordinate_BookActivity");
                        startActivity(intent);
                    }
                }
        );

        btneditcoordinatesheet.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.jhoang.mysqldemo.Coordinate_SheetActivity");
                        startActivity(intent);
                    }
                }
        );

        btnMusicBook.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.jhoang.mysqldemo.Music_BookList");
                        startActivity(intent);
                    }
                }
        );

        btnCoordinateBook.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.jhoang.mysqldemo.Coordinate_BookList");
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.notification:
                Intent notifyIntent = new Intent("com.example.jhoang.mysqldemo.NotificationActivity");
                notifyIntent.putExtra("username", username);
                notifyIntent.putExtra("password", password);
                startActivity(notifyIntent);
                break;
            case R.id.logout:
                String type = "logout";
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, username, password);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
