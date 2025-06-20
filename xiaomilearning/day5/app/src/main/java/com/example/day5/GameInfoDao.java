package com.example.day5;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface GameInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GameInfo> games);

    @Query("SELECT * FROM game_info ORDER BY localId DESC LIMIT :limit OFFSET :offset")
    List<GameInfo> getGamesPaged(int limit, int offset);

    @Query("DELETE FROM game_info")
    void clearAll();

    @Query("SELECT COUNT(*) FROM game_info")
    int getCount();
} 