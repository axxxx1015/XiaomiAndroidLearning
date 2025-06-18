package com.example.hello1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载res/layout中布局文件，也可以在这里直接输
        setContentView(R.layout.activity_main);

    }
}
