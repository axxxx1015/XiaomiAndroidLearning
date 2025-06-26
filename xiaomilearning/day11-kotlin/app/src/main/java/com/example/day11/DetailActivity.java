package com.example.day11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private boolean liked;
    private int type;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        String text = intent.getStringExtra("text");
        String imageUrl = intent.getStringExtra("imageUrl");
        liked = intent.getBooleanExtra("liked", false);
        position = intent.getIntExtra("position", -1);
        TextView textView = findViewById(R.id.text);
        ImageView imageView = findViewById(R.id.image);
        ImageView likeView = findViewById(R.id.like);
        if (type == ListItem.TYPE_IMAGE) {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            textView.setText(text);
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_like)
                .error(R.drawable.ic_liked)
                .into(imageView);
        } else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            textView.setText(text);
        }
        likeView.setImageResource(liked ? R.drawable.ic_liked : R.drawable.ic_like);
        likeView.setOnClickListener(v -> {
            liked = !liked;
            likeView.setImageResource(liked ? R.drawable.ic_liked : R.drawable.ic_like);
        });
        findViewById(R.id.btn_back).setOnClickListener(v -> {
            Intent data = new Intent();
            data.putExtra("position", position);
            data.putExtra("liked", liked);
            setResult(Activity.RESULT_OK, data);
            finish();
        });
    }
} 