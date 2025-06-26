package com.example.day11;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<ListItem> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(dataList, this::onItemClick, this::onLikeClick);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this::refreshData);
        refreshData();
    }

    private void refreshData() {
        dataList.clear();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                int len = 200 + random.nextInt(601);
                String url = "https://picsum.photos/400/" + len;
                dataList.add(new ListItem(ListItem.TYPE_IMAGE, "图片文案" + i, url, false));
            } else {
                dataList.add(new ListItem(ListItem.TYPE_TEXT, "这是第" + i + "条文本", null, false));
            }
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void onItemClick(int position) {
        ListItem item = dataList.get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("type", item.type);
        intent.putExtra("text", item.text);
        intent.putExtra("imageUrl", item.imageUrl);
        intent.putExtra("liked", item.liked);
        intent.putExtra("position", position);
        startActivityForResult(intent, 100);
    }

    private void onLikeClick(int position) {
        ListItem item = dataList.get(position);
        item.liked = !item.liked;
        adapter.notifyItemChanged(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);
            boolean liked = data.getBooleanExtra("liked", false);
            if (position >= 0 && position < dataList.size()) {
                dataList.get(position).liked = liked;
                adapter.notifyItemChanged(position);
            }
        }
    }
} 