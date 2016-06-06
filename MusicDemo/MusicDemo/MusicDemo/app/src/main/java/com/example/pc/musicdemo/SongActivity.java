package com.example.pc.musicdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class SongActivity extends ActionBarActivity {

    private ListView mListView;

    private FindSongs finder;

    private List<Mp3Info> mp3Infos;

    private MyListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findsongs);
        mListView = (ListView) findViewById(R.id.songlistview);
        finder = new FindSongs();
        mp3Infos = finder.getMp3Infos(SongActivity.this.getContentResolver());
        adapter = new MyListViewAdapter(getApplicationContext(), mp3Infos);
        finder.setListAdpter(getApplicationContext(), mp3Infos, mListView);
    }
}
