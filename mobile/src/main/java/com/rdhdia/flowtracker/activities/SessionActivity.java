package com.rdhdia.flowtracker.activities;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rdhdia.flowtracker.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SessionActivity extends AppCompatActivity {

    private static final String TAG = "LOGGING";
    private static final long SEVEN_MINUTES = 420000; // correct value is 420000
    private static final long THREE_MINUTES = 3000; // correct value is 180000
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
    @Bind(R.id.progressFlow) ProgressBar progressFlow;
    @Bind(R.id.progressRest) ProgressBar progressRest;

    private CountDownTimer flowTimer;
    private CountDownTimer restTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        ButterKnife.bind(this);

        addReading.setOnClickListener(new AddReadingListener());
        startSession.setOnClickListener(new StartSessionListener());
        pauseSession.setOnClickListener(new PauseSessionListener());
        stopSession.setOnClickListener(new StopSessionListener());

        progressFlow.setMax((int)SEVEN_MINUTES);
        progressRest.setMax((int)THREE_MINUTES);

    }

    @Override
    protected void onStart() {
        super.onStart();

        flowTimer = new CountDownTimer(SEVEN_MINUTES, ONE_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisUntilFinished = (millisUntilFinished + 500) / 1000 * 1000;
                Log.d(TAG, "Flow onTick: " + millisUntilFinished + "ms");
                long minute = millisUntilFinished / ONE_MINUTE;
                long seconds = (millisUntilFinished / 1000) % 60;

                progressFlow.setProgress((int)SEVEN_MINUTES - (int)millisUntilFinished);
                if ( minute > 0 ) {
                    flowTime.setText(minute + ":" + seconds);
                } else {
                    flowTime.setText("00:" + seconds);
                }

            }

            @Override
            public void onFinish() {
                progressFlow.setProgress(progressFlow.getMax());
                // start 3 minute rest timer
                restTimer.start();
            }
        };

        restTimer = new CountDownTimer(THREE_MINUTES, ONE_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "Rest onTick: " + millisUntilFinished + "ms");
                long minute = millisUntilFinished / ONE_MINUTE;
                long seconds = (millisUntilFinished / 1000) % 60;

                progressRest.setProgress((int)THREE_MINUTES - (int)millisUntilFinished );
                if ( minute > 0 ) {
                    restTime.setText(minute + ":" + seconds);
                } else {
                    restTime.setText("00:" + seconds);
                }
            }

            @Override
            public void onFinish() {
                progressRest.setProgress(progressRest.getMax());

                flowTimer.start();
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
            flowTimer.cancel();
            restTimer.cancel();
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

