package com.example.day5;

import android.content.Context;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameRepository {
    private static final String BASE_URL = "https://hotfix-service-prod.g.mi.com";
    private final GameApiService apiService;
    private final GameInfoDao gameInfoDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public GameRepository(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(GameApiService.class);
        gameInfoDao = AppDatabase.getInstance(context).gameInfoDao();
    }

    public void searchGames(String keyword, int page, int size, Callback<GameSearchResponse> callback) {
        apiService.searchGames(keyword, page, size).enqueue(callback);
    }

    public void saveGamesToDb(List<GameInfo> games) {
        executor.execute(() -> gameInfoDao.insertAll(games));
    }

    public List<GameInfo> getLocalGamesPaged(int limit, int offset) {
        return gameInfoDao.getGamesPaged(limit, offset);
    }

    public void clearLocalGames() {
        executor.execute(gameInfoDao::clearAll);
    }

    public int getLocalGamesCount() {
        return gameInfoDao.getCount();
    }
} 