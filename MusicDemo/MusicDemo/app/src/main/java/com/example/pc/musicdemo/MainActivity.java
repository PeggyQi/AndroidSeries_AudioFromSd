package com.example.pc.musicdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;

    private Button mFind;

    private FindSongs finder;

    private List<Mp3Info> mp3Infos;

    private MyListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview);
        finder = new FindSongs();
        mp3Infos = finder.getMp3Infos(MainActivity.this.getContentResolver());
        adapter = new MyListViewAdapter(getApplicationContext(), mp3Infos);
        finder.setListAdpter(getApplicationContext(), mp3Infos, mListView);
    }
}
