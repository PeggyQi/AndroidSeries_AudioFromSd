package com.example.pc.musicdemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void findSongs(View v)
    {
            startActivity(new Intent(MainActivity.this,SongActivity.class));
    }

    public void findVideos(View v)
    {
        Log.i("MainActivity","findVideos");
        Intent i=new Intent(MainActivity.this,VideoActivity.class);
        startActivity(i);
    }

    public void findApk(View v)
    {
        Log.i("MainActivity","findApk");
        Intent i=new Intent(MainActivity.this,ApplicationActivity.class);
        startActivity(i);
    }
}
