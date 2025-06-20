package com.example.day5;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private EditText etSearch;
    private Button btnSearch;
    private RecyclerView rvResult;
    private SwipeRefreshLayout swipeRefresh;
    private GameInfoAdapter adapter;
    private GameRepository repository;
    private int currentPage = 1;
    private final int pageSize = 10;
    private String lastKeyword = "";
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private final List<GameInfo> allResults = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        etSearch = view.findViewById(R.id.et_search);
        btnSearch = view.findViewById(R.id.btn_search);
        rvResult = view.findViewById(R.id.rv_search_result);
        swipeRefresh = view.findViewById(R.id.swipe_refresh);
        adapter = new GameInfoAdapter();
        rvResult.setLayoutManager(new LinearLayoutManager(getContext()));
        rvResult.setAdapter(adapter);
        repository = new GameRepository(requireContext());
        btnSearch.setOnClickListener(v -> startSearch());
        swipeRefresh.setOnRefreshListener(this::refresh);
        rvResult.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading && !isLastPage && lm != null && lm.findLastVisibleItemPosition() >= adapter.getItemCount() - 2) {
                    loadMore();
                }
            }
        });
        return view;
    }

    private void startSearch() {
        String keyword = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(keyword)) {
            Toast.makeText(getContext(), "请输入关键词", Toast.LENGTH_SHORT).show();
            return;
        }
        lastKeyword = keyword;
        currentPage = 1;
        isLastPage = false;
        allResults.clear();
        adapter.setData(new ArrayList<>());
        swipeRefresh.setRefreshing(true);
        fetchData(keyword, currentPage);
    }

    private void refresh() {
        if (!TextUtils.isEmpty(lastKeyword)) {
            currentPage = 1;
            isLastPage = false;
            allResults.clear();
            adapter.setData(new ArrayList<>());
            fetchData(lastKeyword, currentPage);
        } else {
            swipeRefresh.setRefreshing(false);
        }
    }

    private void loadMore() {
        if (!isLoading && !isLastPage) {
            fetchData(lastKeyword, currentPage + 1);
        }
    }

    private void fetchData(String keyword, int page) {
        isLoading = true;
        repository.searchGames(keyword, page, pageSize, new Callback<GameSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<GameSearchResponse> call, @NonNull Response<GameSearchResponse> response) {
                swipeRefresh.setRefreshing(false);
                isLoading = false;
                if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                    List<GameInfo> records = response.body().data.records;
                    if (page == 1) {
                        allResults.clear();
                        adapter.setData(records);
                    } else {
                        adapter.appendData(records);
                    }
                    allResults.addAll(records);
                    repository.saveGamesToDb(records);
                    currentPage = response.body().data.current;
                    isLastPage = (currentPage >= response.body().data.pages);
                } else {
                    Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<GameSearchResponse> call, @NonNull Throwable t) {
                swipeRefresh.setRefreshing(false);
                isLoading = false;
                Toast.makeText(getContext(), "网络错误: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
} 