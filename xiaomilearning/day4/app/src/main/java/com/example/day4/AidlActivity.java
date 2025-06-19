package com.example.day4;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class AidlActivity extends AppCompatActivity implements KeyAdapter.OnKeyClickListener {
    private ICalcAidlInterface calcService;
    private boolean isBound = false;
    private TextView tvDisplay;
    private StringBuilder input = new StringBuilder();
    private Integer firstNum = null;
    private String operator = null;
    private boolean isResult = false;

    private final List<String> keyList = Arrays.asList(
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        tvDisplay = findViewById(R.id.tv_display);
        RecyclerView rvKeys = findViewById(R.id.rv_keys);
        rvKeys.setLayoutManager(new GridLayoutManager(this, 4));
        rvKeys.setAdapter(new KeyAdapter(keyList, this));

        Button btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(v -> tvDisplay.setText("按钮已点击！"));

        tvDisplay.setText("调试显示");
        Intent intent = new Intent(this, CalcService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    public void onKeyClick(String key) {
        if ("C".equals(key)) {
            input.setLength(0);
            firstNum = null;
            operator = null;
            isResult = false;
            tvDisplay.setText("0");
            return;
        }
        if (key.equals("=") || key.equals("+") || key.equals("-") || key.equals("*") || key.equals("/")) {
            if (firstNum == null && input.length() > 0) {
                firstNum = Integer.parseInt(input.toString());
                input.setLength(0);
            }
            if (operator != null && input.length() > 0) {
                int secondNum = Integer.parseInt(input.toString());
                doCalc(firstNum, secondNum, operator);
                operator = null;
                input.setLength(0);
                isResult = true;
                if (!"=".equals(key)) {
                    operator = key;
                    isResult = false;
                }
            } else if (!"=".equals(key)) {
                operator = key;
                isResult = false;
            }
            return;
        }
        if (isResult) {
            input.setLength(0);
            isResult = false;
        }
        input.append(key);
        tvDisplay.setText(input.toString());
    }

    private void doCalc(Integer a, int b, String op) {
        if (!isBound || a == null) {
            Toast.makeText(this, "服务未绑定", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("/".equals(op) && b == 0) {
            tvDisplay.setText("除数不能为0");
            firstNum = null;
            return;
        }
        try {
            int result = calcService.calculate(a, b, op);
            tvDisplay.setText(String.valueOf(result));
            firstNum = result;
        } catch (RemoteException e) {
            tvDisplay.setText("计算错误");
            firstNum = null;
        }
    }

    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            calcService = ICalcAidlInterface.Stub.asInterface(service);
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBound) {
            unbindService(conn);
        }
    }
} 