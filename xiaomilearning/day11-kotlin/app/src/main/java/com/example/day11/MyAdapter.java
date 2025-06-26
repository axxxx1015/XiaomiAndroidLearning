package com.example.day11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ListItem> dataList;
    private final OnItemClickListener itemClickListener;
    private final OnLikeClickListener likeClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public interface OnLikeClickListener {
        void onLikeClick(int position);
    }
    public MyAdapter(List<ListItem> dataList, OnItemClickListener itemClickListener, OnLikeClickListener likeClickListener) {
        this.dataList = dataList;
        this.itemClickListener = itemClickListener;
        this.likeClickListener = likeClickListener;
    }
    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).type;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ListItem.TYPE_IMAGE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
            return new TextViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListItem item = dataList.get(position);
        if (holder instanceof ImageViewHolder) {
            ImageViewHolder vh = (ImageViewHolder) holder;
            vh.text.setText(item.text);
            Glide.with(holder.itemView.getContext())
                .load(item.imageUrl)
                .placeholder(R.drawable.ic_like)
                .error(R.drawable.ic_liked)
                .into(vh.image);
            vh.like.setImageResource(item.liked ? R.drawable.ic_liked : R.drawable.ic_like);
            vh.like.setOnClickListener(v -> likeClickListener.onLikeClick(position));
            vh.itemView.setOnClickListener(v -> itemClickListener.onItemClick(position));
        } else if (holder instanceof TextViewHolder) {
            TextViewHolder vh = (TextViewHolder) holder;
            vh.text.setText(item.text);
            vh.like.setImageResource(item.liked ? R.drawable.ic_liked : R.drawable.ic_like);
            vh.like.setOnClickListener(v -> likeClickListener.onLikeClick(position));
            vh.itemView.setOnClickListener(v -> itemClickListener.onItemClick(position));
        }
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    static class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView image, like;
        ImageViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            image = itemView.findViewById(R.id.image);
            like = itemView.findViewById(R.id.like);
        }
    }
    static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView like;
        TextViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            like = itemView.findViewById(R.id.like);
        }
    }
} 