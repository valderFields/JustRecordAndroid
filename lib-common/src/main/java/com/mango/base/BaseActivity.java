package com.mango.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mango.lib_common.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base2);
        getSupportActionBar().hide();


    }
}
