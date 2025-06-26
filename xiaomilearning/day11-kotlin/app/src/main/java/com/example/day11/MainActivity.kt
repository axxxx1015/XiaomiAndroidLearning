package com.example.day11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private val dataList = mutableListOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(dataList, ::onItemClick, ::onLikeClick)
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener { refreshData() }
        refreshData()
    }

    private fun refreshData() {
        dataList.clear()
        repeat(20) { i ->
            if (i % 2 == 0) {
                val len = (200..800).random()
                val url = "https://picsum.photos/400/$len"
                dataList.add(ListItem(ListItem.TYPE_IMAGE, "图片文案$i", url, false))
            } else {
                dataList.add(ListItem(ListItem.TYPE_TEXT, "这是第${i}条文本", null, false))
            }
        }
        adapter.notifyDataSetChanged()
        swipeRefreshLayout.isRefreshing = false
    }

    private fun onItemClick(position: Int) {
        val item = dataList[position]
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("type", item.type)
            putExtra("text", item.text)
            putExtra("imageUrl", item.imageUrl)
            putExtra("liked", item.liked)
            putExtra("position", position)
        }
        startActivityForResult(intent, 100)
    }

    private fun onLikeClick(position: Int) {
        val item = dataList[position]
        item.liked = !item.liked
        adapter.notifyItemChanged(position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            val position = data.getIntExtra("position", -1)
            val liked = data.getBooleanExtra("liked", false)
            if (position in dataList.indices) {
                dataList[position].liked = liked
                adapter.notifyItemChanged(position)
            }
        }
    }
} 