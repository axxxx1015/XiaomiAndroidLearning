package com.example.day2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载res/layout中布局文件，也可以在这里直接输
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        btn1 = findViewById(R.id.btn_fragment1);
        btn2 = findViewById(R.id.btn_fragment2);
        btn3 = findViewById(R.id.btn_fragment3);

        fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        btn1.setOnClickListener(v -> viewPager.setCurrentItem(0));
        btn2.setOnClickListener(v -> viewPager.setCurrentItem(1));
        btn3.setOnClickListener(v -> viewPager.setCurrentItem(2));

        // 可选：ViewPager页面切换时同步按钮状态（比如高亮当前按钮）
    }
}
