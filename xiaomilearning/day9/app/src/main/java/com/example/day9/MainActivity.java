package com.example.day9;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tvOrderStatus;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup coffeeTypeGroup = findViewById(R.id.coffeeTypeGroup);
        RadioButton rbEspresso = findViewById(R.id.rbEspresso);
        RadioButton rbLatte = findViewById(R.id.rbLatte);
        CheckBox cbMilk = findViewById(R.id.cbMilk);
        CheckBox cbSugar = findViewById(R.id.cbSugar);
        RadioGroup paymentGroup = findViewById(R.id.paymentGroup);
        RadioButton rbAlipay = findViewById(R.id.rbAlipay);
        RadioButton rbWechat = findViewById(R.id.rbWechat);
        Button btnOrder = findViewById(R.id.btnOrder);
        tvOrderStatus = findViewById(R.id.tvOrderStatus);

        rbEspresso.setChecked(true);
        rbAlipay.setChecked(true);

        btnOrder.setOnClickListener(v -> {
            // 1. 获取咖啡类型
            String coffeeType = rbEspresso.isChecked() ? "Espresso" : "Latte";
            Coffee coffee = CoffeeFactory.createCoffee(coffeeType);
            // 2. 配料
            if (cbMilk.isChecked()) coffee = new MilkDecorator(coffee);
            if (cbSugar.isChecked()) coffee = new SugarDecorator(coffee);
            double price = coffee.cost();
            // 3. 支付方式
            PaymentStrategy payment = rbAlipay.isChecked() ? new AlipayStrategy() : new WechatPayStrategy();
            // 4. 下单
            StringBuilder statusBuilder = new StringBuilder();
            statusBuilder.append("下单: ").append(coffee.getDescription()).append(", 总价: ").append(price).append("\n");
            // 5. 支付
            statusBuilder.append("支付方式: ").append(rbAlipay.isChecked() ? "支付宝" : "微信").append("\n");
            statusBuilder.append("支付中...\n");
            tvOrderStatus.setText(statusBuilder.toString());
            payment.pay(price);
            // 6. 订单与观察者
            Order order = new Order(coffee.getDescription());
            OrderObserver customer = o -> appendStatus("顾客收到通知: 订单状态 - " + o.getStatus());
            OrderObserver kitchen = o -> appendStatus("厨房收到通知: 订单状态 - " + o.getStatus());
            OrderManager.getInstance().addObserver(customer);
            OrderManager.getInstance().addObserver(kitchen);
            // 7. 模拟订单状态变化
            handler.postDelayed(() -> {
                order.setStatus(Order.Status.PAID);
                OrderManager.getInstance().notifyOrderStatusChanged(order);
            }, 800);
            handler.postDelayed(() -> {
                order.setStatus(Order.Status.MAKING);
                OrderManager.getInstance().notifyOrderStatusChanged(order);
            }, 1800);
            handler.postDelayed(() -> {
                order.setStatus(Order.Status.FINISHED);
                OrderManager.getInstance().notifyOrderStatusChanged(order);
                OrderManager.getInstance().removeObserver(customer);
                OrderManager.getInstance().removeObserver(kitchen);
            }, 3000);
        });
    }

    private void appendStatus(String msg) {
        runOnUiThread(() -> {
            tvOrderStatus.append(msg + "\n");
        });
    }
} 