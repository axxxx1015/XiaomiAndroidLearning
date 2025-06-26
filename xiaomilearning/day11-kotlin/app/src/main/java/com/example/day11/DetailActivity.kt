package com.example.day11

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private var liked = false
    private var type = 0
    private var position = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent = intent
        type = intent.getIntExtra("type", 0)
        val text = intent.getStringExtra("text")
        val imageUrl = intent.getStringExtra("imageUrl")
        liked = intent.getBooleanExtra("liked", false)
        position = intent.getIntExtra("position", -1)
        val textView = findViewById<TextView>(R.id.text)
        val imageView = findViewById<ImageView>(R.id.image)
        val likeView = findViewById<ImageView>(R.id.like)
        if (type == ListItem.TYPE_IMAGE) {
            textView.visibility = View.VISIBLE
            imageView.visibility = View.VISIBLE
            textView.text = text
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_like)
                .error(R.drawable.ic_liked)
                .into(imageView)
        } else {
            textView.visibility = View.VISIBLE
            imageView.visibility = View.GONE
            textView.text = text
        }
        likeView.setImageResource(if (liked) R.drawable.ic_liked else R.drawable.ic_like)
        likeView.setOnClickListener {
            liked = !liked
            likeView.setImageResource(if (liked) R.drawable.ic_liked else R.drawable.ic_like)
        }
        findViewById<View>(R.id.btn_back).setOnClickListener {
            val data = Intent().apply {
                putExtra("position", position)
                putExtra("liked", liked)
            }
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }
} 