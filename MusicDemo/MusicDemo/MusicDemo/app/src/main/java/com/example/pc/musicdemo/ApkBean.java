package com.example.pc.musicdemo;

public class ApkBean {
    private long id;
    private long album_id;
    private String title;
    private long size;
    private String url;
    private String album;
    private boolean isFavorite = false;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){return this.id;}

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){return this.title;}

    public void setSize(long size){this.size = size;}

    public long getSize(){return this.size;}

    public void setUrl(String url){this.url = url;}

    public String getUrl(){return this.url;}

    public void setAlbum(String album){this.album = album;}

}

