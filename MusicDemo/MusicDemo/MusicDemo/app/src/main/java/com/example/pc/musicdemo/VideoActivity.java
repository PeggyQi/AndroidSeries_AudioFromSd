package com.example.pc.musicdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class VideoActivity extends ActionBarActivity {

    private ListView mListView;

    private FindVideos findVideos;

    private List<VideoBean> videoInfos;

    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findvideos);
        mListView = (ListView) findViewById(R.id.videolistview);
        findVideos=new FindVideos();
        videoInfos = findVideos.getVideoInfos(VideoActivity.this.getContentResolver());
        videoAdapter = new VideoAdapter(getApplicationContext(), videoInfos);
        findVideos.setListAdpter(getApplicationContext(), videoInfos, mListView);
    }
}
