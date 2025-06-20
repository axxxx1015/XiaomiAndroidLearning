package com.example.day5;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "game_info")
public class GameInfo {
    @PrimaryKey(autoGenerate = true)
    public int localId;
    public int id;
    public String gameName;
    public String icon;
    public String introduction;
    public String brief;
    public String versionName;
    public String apkUrl;
    public String tags;
    public double score;
    public String playNumFormat;
    public String createTime;
    // 可根据实际接口补充字段
} 