package com.example.haier.sheji;

import android.os.Bundle;

import com.zhy.autolayout.AutoLayoutActivity;

public class BaseActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
