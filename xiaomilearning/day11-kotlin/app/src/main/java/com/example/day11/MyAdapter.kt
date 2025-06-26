package com.example.day11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(
    private val dataList: List<ListItem>,
    private val itemClickListener: (Int) -> Unit,
    private val likeClickListener: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int = dataList[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == ListItem.TYPE_IMAGE) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            ImageViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
            TextViewHolder(view)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        if (holder is ImageViewHolder) {
            holder.text.text = item.text
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.ic_like)
                .error(R.drawable.ic_liked)
                .into(holder.image)
            holder.like.setImageResource(if (item.liked) R.drawable.ic_liked else R.drawable.ic_like)
            holder.like.setOnClickListener { likeClickListener(position) }
            holder.itemView.setOnClickListener { itemClickListener(position) }
        } else if (holder is TextViewHolder) {
            holder.text.text = item.text
            holder.like.setImageResource(if (item.liked) R.drawable.ic_liked else R.drawable.ic_like)
            holder.like.setOnClickListener { likeClickListener(position) }
            holder.itemView.setOnClickListener { itemClickListener(position) }
        }
    }

    override fun getItemCount(): Int = dataList.size

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text)
        val image: ImageView = itemView.findViewById(R.id.image)
        val like: ImageView = itemView.findViewById(R.id.like)
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text)
        val like: ImageView = itemView.findViewById(R.id.like)
    }
} 