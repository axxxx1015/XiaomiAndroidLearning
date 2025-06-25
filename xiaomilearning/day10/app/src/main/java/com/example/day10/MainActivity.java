package com.example.day10;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText stackEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);
        Button btnAnr = findViewById(R.id.btn_anr);
        stackEditText = findViewById(R.id.edit_stack);
        // 按钮点击后触发ANR
        btnAnr.setOnClickListener(v -> triggerAnr());
        // 启动一个线程检测ANR
        startAnrWatchDog();
    }
    private void triggerAnr() {
        // 故意让主线程卡住，模拟ANR
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void startAnrWatchDog() {
        Thread anrWatchDog = new Thread(() -> {
            while (true) {
                final boolean[] responded = {false};
                Handler handler = new Handler(Looper.getMainLooper());
                // 发消息到主线程，看看主线程能不能及时响应
                handler.post(() -> responded[0] = true);
                try {
                    Thread.sleep(5000); // 5秒没响应就算ANR
                } catch (InterruptedException e) {
                }
                if (!responded[0]) {
                    // 检测到ANR，获取主线程堆栈信息
                    StringBuilder sb = new StringBuilder();
                    StackTraceElement[] stack = Looper.getMainLooper().getThread().getStackTrace();
                    for (StackTraceElement element : stack) {
                        sb.append(element.toString()).append("\n");
                    }
                    showAnrStack(sb.toString().trim());
                }
            }
        });
        anrWatchDog.setDaemon(true);
        anrWatchDog.start();
    }
    public void showAnrStack(String stack) {
        runOnUiThread(() -> stackEditText.setText(stack));
    }
} 