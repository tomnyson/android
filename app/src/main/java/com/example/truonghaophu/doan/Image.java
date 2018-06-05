package com.example.truonghaophu.doan;

public class Image {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public  String name;

    public Image(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public  String url;
}
