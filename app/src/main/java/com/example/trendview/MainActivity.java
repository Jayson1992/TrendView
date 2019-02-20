package com.example.trendview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.trendview.View.TrendView;

public class MainActivity extends AppCompatActivity {
    private TrendView mTrendView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrendView = findViewById(R.id.activity_main_middle);
        mTrendView.setItemSelectedListener(new TrendView.OnItemSelectedListener() {
            @Override
            public void itemSelected(int position) {
                Toast.makeText(MainActivity.this, "您点击了-----" + position + "行!!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
