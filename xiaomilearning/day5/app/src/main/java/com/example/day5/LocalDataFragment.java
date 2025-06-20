package com.example.day5;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocalDataFragment extends Fragment {
    private RecyclerView rvLocalData;
    private GameInfoAdapter adapter;
    private GameRepository repository;
    private Handler handler;
    private int offset = 0;
    private final int pageSize = 5;
    private Runnable refreshTask;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_data, container, false);
        rvLocalData = view.findViewById(R.id.rv_local_data);
        adapter = new GameInfoAdapter();
        rvLocalData.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLocalData.setAdapter(adapter);
        repository = new GameRepository(requireContext());
        handler = new Handler(Looper.getMainLooper());
        refreshTask = this::refreshData;
        handler.post(refreshTask);
        return view;
    }

    private void refreshData() {
        executor.execute(() -> {
            int total = repository.getLocalGamesCount();
            List<GameInfo> games = null;
            int nextOffset = offset;
            if (total != 0) {
                if (offset >= total) nextOffset = 0;
                games = repository.getLocalGamesPaged(pageSize, nextOffset);
                nextOffset += pageSize;
                if (nextOffset >= total) nextOffset = 0;
            }
            final List<GameInfo> finalGames = games;
            final int finalNextOffset = nextOffset;
            handler.post(() -> {
                if (total == 0) {
                    adapter.setData(null);
                } else {
                    adapter.setData(finalGames);
                    offset = finalNextOffset;
                }
                handler.postDelayed(refreshTask, 5000);
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(refreshTask);
    }
} 