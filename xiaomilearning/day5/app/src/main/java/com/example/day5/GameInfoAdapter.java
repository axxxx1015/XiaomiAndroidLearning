package com.example.day5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class GameInfoAdapter extends RecyclerView.Adapter<GameInfoAdapter.GameViewHolder> {
    private final List<GameInfo> data = new ArrayList<>();

    public void setData(List<GameInfo> newData) {
        data.clear();
        if (newData != null) data.addAll(newData);
        notifyDataSetChanged();
    }

    public void appendData(List<GameInfo> moreData) {
        int start = data.size();
        if (moreData != null) {
            data.addAll(moreData);
            notifyItemRangeInserted(start, moreData.size());
        }
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_info, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        GameInfo game = data.get(position);
        holder.tvName.setText(game.gameName);
        holder.tvDesc.setText(game.brief);
        Glide.with(holder.itemView.getContext()).load(game.icon).into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName, tvDesc;
        GameViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
} 