package com.example.day6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private WaterfallAdapter adapter;
    private List<String> imageUrls = new ArrayList<>();
    private Random random = new Random();
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);

        // 设置布局管理器为瀑布流
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        // 初始化 Adapter
        adapter = new WaterfallAdapter(this, imageUrls);
        recyclerView.setAdapter(adapter);

        // 设置下拉刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }
        });

        // 设置上拉加载监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                
                if (!isLoading && !swipeRefreshLayout.isRefreshing()) {
                    StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                    if (layoutManager != null) {
                        int[] lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null);
                        int lastVisibleItemPosition = getMaxPosition(lastVisibleItemPositions);
                        int totalItemCount = layoutManager.getItemCount();
                        
                        if (lastVisibleItemPosition >= totalItemCount - 3) {
                            loadData(false);
                        }
                    }
                }
            }
        });

        // 首次加载数据
        loadData(true);
    }

    private int getMaxPosition(int[] positions) {
        int max = positions[0];
        for (int position : positions) {
            if (position > max) {
                max = position;
            }
        }
        return max;
    }

    private void loadData(final boolean isRefresh) {
        if (isLoading) return;
        
        isLoading = true;
        new Thread(() -> {
            final List<String> newData = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                int height = random.nextInt(601) + 200; // 200-800
                newData.add("https://picsum.photos/400/" + height);
            }

            runOnUiThread(() -> {
                if (isRefresh) {
                    imageUrls.clear();
                    swipeRefreshLayout.setRefreshing(false);
                }
                imageUrls.addAll(newData);
                adapter.notifyDataSetChanged();
                isLoading = false;
            });
        }).start();
    }
} 