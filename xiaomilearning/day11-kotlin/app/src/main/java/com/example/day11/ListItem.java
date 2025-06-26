package com.example.day11;

public class ListItem {
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMAGE = 1;
    public int type;
    public String text;
    public String imageUrl;
    public boolean liked;
    public ListItem(int type, String text, String imageUrl, boolean liked) {
        this.type = type;
        this.text = text;
        this.imageUrl = imageUrl;
        this.liked = liked;
    }
} 