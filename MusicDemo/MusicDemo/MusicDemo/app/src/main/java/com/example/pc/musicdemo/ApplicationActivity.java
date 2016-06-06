package com.example.pc.musicdemo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ApplicationActivity extends ActionBarActivity {

    private ListView mListView;

    private ApkAdapter apkAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findapk);
        mListView = (ListView) findViewById(R.id.apklistview);
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++)
        {
            PackageInfo packageInfo = packs.get(i);

           ApplicationInfo applicationInfo=packageInfo.applicationInfo;
            if((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {//区分是否为第三方应用
                packs.remove(i--);

            }

        }

        apkAdapter=new ApkAdapter(this,packs);
        mListView.setAdapter(apkAdapter);

    }
}
