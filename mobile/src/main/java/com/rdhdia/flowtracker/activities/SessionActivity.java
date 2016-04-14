package com.rdhdia.flowtracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.rdhdia.flowtracker.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SessionActivity extends AppCompatActivity {

    @Bind(R.id.btnAdd) Button addReading;
    @Bind(R.id.btnStartSession) Button startSession;
    @Bind(R.id.edtReadingValue) EditText readingInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

    }
}

