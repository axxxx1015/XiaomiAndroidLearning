package com.example.day4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class KeyAdapter extends RecyclerView.Adapter<KeyAdapter.KeyViewHolder> {
    public interface OnKeyClickListener {
        void onKeyClick(String key);
    }
    private final List<String> keyList;
    private final OnKeyClickListener listener;

    public KeyAdapter(List<String> keyList, OnKeyClickListener listener) {
        this.keyList = keyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public KeyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_key, parent, false);
        return new KeyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeyViewHolder holder, int position) {
        String key = keyList.get(position);
        holder.tvKey.setText(key);
        holder.tvKey.setOnClickListener(v -> listener.onKeyClick(key));
    }

    @Override
    public int getItemCount() {
        return keyList.size();
    }

    static class KeyViewHolder extends RecyclerView.ViewHolder {
        TextView tvKey;
        public KeyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKey = itemView.findViewById(R.id.tv_key);
        }
    }
} 