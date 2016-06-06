package com.example.pc.musicdemo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

class ApkViewHolder{
    public TextView apk_title;//music 名称
    public ImageView apk_icon;//music 封面
    public TextView apk_size;//music 大小

}

public class ApkAdapter extends BaseAdapter {

    private Context context;        //上下文对象引用
    private List<PackageInfo> packageInfos;   //存放packageInfo引用的集合
    private PackageInfo packageInfo;        //packageInfo对象引用
    private ApplicationInfo applicationInfo;
    private int pos = -1;           //列表位置
    private ApkViewHolder viewHolder;


    public ApkAdapter(Context context, List<PackageInfo> packageInfos) {
        this.context = context;
        this.packageInfos = packageInfos;
    }


    @Override
    public int getCount() {
        return packageInfos.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder = null;
        if (convertView == null) {
            viewHolder = new ApkViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_apk_list, null);
            viewHolder.apk_title = (TextView) convertView.findViewById(R.id.apk_title);
            viewHolder.apk_icon = (ImageView) convertView.findViewById(R.id.apk_icon);
            viewHolder.apk_size = (TextView) convertView.findViewById(R.id.apk_size);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ApkViewHolder) convertView.getTag();
        }
        packageInfo = packageInfos.get(position);

        applicationInfo=packageInfo.applicationInfo;

        Drawable drawable=context.getPackageManager().getApplicationIcon(applicationInfo);
        viewHolder.apk_icon.setImageDrawable(drawable);
        String dir=applicationInfo.publicSourceDir;
        int size=Integer.valueOf((int)new File(dir).length());
        viewHolder.apk_size.setText(formatSize(size)+"MB");//显示大小
        viewHolder.apk_title.setText(context.getPackageManager().getApplicationLabel(applicationInfo).toString());         //显示标题
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    public static String formatTime(Long time) {                     //将歌曲的时间转换为分秒的制度
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";

        if (min.length() < 2)
            min = "0" + min;
        switch (sec.length()) {
            case 4:
                sec = "0" + sec;
                break;
            case 3:
                sec = "00" + sec;
                break;
            case 2:
                sec = "000" + sec;
                break;
            case 1:
                sec = "0000" + sec;
                break;
        }
        return min + ":" + sec.trim().substring(0, 2);
    }

    public double formatSize(long size)//大小转换单位
    {
        long size1 = (long) (size / 1024.0 / 1024.0 * 100);
        Log.e("MusicDemo", String.valueOf(size1));
        long sizel = Math.round(size1);
        Log.e("MusicDemo", String.valueOf(sizel));
        double sized = sizel / 100.0;
        Log.e("MusicDemo", String.valueOf(sized));
        return sized;
    }

    /**
     * 获取视频的缩略图
     * 先通过ThumbnailUtils来创建一个视频的缩略图，然后再利用ThumbnailUtils来生成指定大小的缩略图。
     * 如果想要的缩略图的宽和高都小于MICRO_KIND，则类型要使用MICRO_KIND作为kind的值，这样会节省内存。
     * @param videoPath 视频的路径
     * @param width 指定输出视频缩略图的宽度
     * @param height 指定输出视频缩略图的高度度
     * @param kind 参照MediaStore.Images.Thumbnails类中的常量MINI_KIND和MICRO_KIND。
     *            其中，MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
     * @return 指定大小的视频缩略图
     */
    private Bitmap getVideoThumbnail(String videoPath, int width, int height,
                                     int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        System.out.println("w"+bitmap.getWidth());
        System.out.println("h"+bitmap.getHeight());
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    /*
     * 采用了新的办法获取APK图标，之前的失败是因为android中存在的一个BUG,通过
     * appInfo.publicSourceDir = apkPath;来修正这个问题，详情参见:
     * http://code.google.com/p/android/issues/detail?id=9151
     */
    public static Drawable getApkIcon(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            appInfo.sourceDir = apkPath;
            appInfo.publicSourceDir = apkPath;
            try {
                return appInfo.loadIcon(pm);
            } catch (OutOfMemoryError e) {
                Log.e("ApkIconLoader", e.toString());
            }
        }
        return null;
    }
}

