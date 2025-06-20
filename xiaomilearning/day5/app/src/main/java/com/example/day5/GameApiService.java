package com.example.day5;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameApiService {
    @GET("/quick-game/game/search")
    Call<GameSearchResponse> searchGames(
        @Query("search") String search,
        @Query("current") int current,
        @Query("size") int size
    );
} 