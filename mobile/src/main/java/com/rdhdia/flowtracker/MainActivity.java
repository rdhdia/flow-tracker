package com.rdhdia.flowtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setClassName(BuildConfig.APPLICATION_ID,
                BuildConfig.APPLICATION_ID + ".activities.SessionActivity");
        startActivity(intent);
        finish();
    }
}
