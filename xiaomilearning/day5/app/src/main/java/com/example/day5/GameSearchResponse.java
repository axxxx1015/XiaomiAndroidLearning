package com.example.day5;

import java.util.List;

public class GameSearchResponse {
    public int code;
    public String msg;
    public Data data;

    public static class Data {
        public List<GameInfo> records;
        public int total;
        public int size;
        public int current;
        public int pages;
    }
}