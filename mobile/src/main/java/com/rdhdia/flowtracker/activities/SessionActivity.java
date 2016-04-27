package com.rdhdia.flowtracker.activities;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rdhdia.flowtracker.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SessionActivity extends AppCompatActivity {

    private static final long SEVEN_MINUTES = 420000;
    private static final long THREE_MINUTES = 180000;
    private static final long ONE_MINUTE = 60000;
    private static final long ONE_SECOND = 1000;

    @Bind(R.id.btnAdd) Button addReading;
    @Bind(R.id.btnStart) Button startSession;
    @Bind(R.id.btnPause) Button pauseSession;
    @Bind(R.id.btnStop) Button stopSession;
    @Bind(R.id.edtReadingValue) EditText readingInput;
    @Bind(R.id.lblFlowCountdown) TextView flowTime;
    @Bind(R.id.lblRestCountdown) TextView restTime;
    @Bind(R.id.recyclerReadings) RecyclerView recyclerReadings;

    private CountDownTimer flowTimer;
    private CountDownTimer restTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        addReading.setOnClickListener(new AddReadingListener());
        startSession.setOnClickListener(new StartSessionListener());
        pauseSession.setOnClickListener(new PauseSessionListener());
        stopSession.setOnClickListener(new StopSessionListener());
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 418000 millis
        // 418 seconds


        flowTimer = new CountDownTimer(SEVEN_MINUTES, ONE_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minute = millisUntilFinished / ONE_MINUTE;
                long seconds = (millisUntilFinished / 1000) % 60;

                if ( minute > 0 ) {
                    flowTime.setText(minute + ":" + seconds);
                } else {
                    flowTime.setText("00:" + seconds);
                }
            }

            @Override
            public void onFinish() {

            }
        };

        restTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class StartSessionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            flowTimer.start();
        }
    }

    private class StopSessionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

        }
    }

    private class PauseSessionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

        }
    }

    private class AddReadingListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

        }
    }


}

