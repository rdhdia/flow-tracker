package com.rdhdia.flowtracker.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rdhdia.flowtracker.R;
import com.rdhdia.flowtracker.models.Project;
import com.rdhdia.flowtracker.models.Reading;
import com.rdhdia.flowtracker.models.Session;
import com.rdhdia.flowtracker.utils.DatabaseHandler;
import com.rdhdia.flowtracker.views.adapters.ReadingAdapter;

import java.util.List;

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
    private Session session;
    private int orderCount;
    private DatabaseHandler db;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        ButterKnife.bind(this);
        db = new DatabaseHandler(this);
        bundle = getIntent().getExtras();

        addReading.setOnClickListener(new AddReadingListener());
        startSession.setOnClickListener(new StartSessionListener());
        pauseSession.setOnClickListener(new PauseSessionListener());
        stopSession.setOnClickListener(new StopSessionListener());

        progressFlow.setMax((int)SEVEN_MINUTES);
        progressRest.setMax((int)THREE_MINUTES);

        recyclerReadings.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SessionActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerReadings.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Create new session
        session = new Session();

        // generate new session id here

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
        showReadings();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showReadings() {
        List<Reading> readings = db.getAllReading();

        if ( readings.size() > 0 ) {
            ReadingAdapter adapter = new ReadingAdapter(readings, SessionActivity.this);
            recyclerReadings.setAdapter(adapter);
        } else {
            Toast.makeText(SessionActivity.this, "No readings found", Toast.LENGTH_SHORT).show();
        }
    }

    private void enableControlButtons() {
        setAddButtonEnabled(true);
        setPauseButtonEnabled(true);
        setStopButtonEnabled(true);
    }

    private void setAddButtonEnabled(boolean enabled) {
        if ( enabled ) {
            addReading.setEnabled(true);
            addReading.setTextColor(ContextCompat.getColor(this, R.color.buttonTextEnabled));
        } else {
            addReading.setEnabled(false);
            addReading.setTextColor(ContextCompat.getColor(this, R.color.buttonTextDisabled));
        }
    }

    private void setPauseButtonEnabled(boolean enabled) {
        if ( enabled ) {
            pauseSession.setEnabled(true);
            pauseSession.setTextColor(ContextCompat.getColor(this, R.color.buttonTextEnabled));
        } else {
            pauseSession.setEnabled(false);
            pauseSession.setTextColor(ContextCompat.getColor(this, R.color.buttonTextDisabled));
        }
    }

    private void setStopButtonEnabled(boolean enabled) {
        if ( enabled ) {
            stopSession.setEnabled(true);
            stopSession.setTextColor(ContextCompat.getColor(this, R.color.buttonTextEnabled));
        } else {
            stopSession.setEnabled(false);
            stopSession.setTextColor(ContextCompat.getColor(this, R.color.buttonTextDisabled));
        }
    }

    private void setStartButtonEnabled(boolean enabled) {
        if ( enabled ) {
            startSession.setEnabled(true);
            startSession.setTextColor(ContextCompat.getColor(this, R.color.buttonTextEnabled));
        } else {
            startSession.setEnabled(false);
            startSession.setTextColor(ContextCompat.getColor(this, R.color.buttonTextDisabled));
        }
    }

    private class StartSessionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            flowTimer.start();
            enableControlButtons();
            setStartButtonEnabled(false);
        }
    }

    private class StopSessionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            flowTimer.cancel();
            restTimer.cancel();

            // Handle all readings, save to session and store to database
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
            String input = readingInput.getText().toString();

            // generate time (millis)
            long time = System.currentTimeMillis();

            Reading reading = new Reading();
            reading.setId(getNextKey());
            reading.setTime(String.valueOf(time));
            reading.setFlowValue(input);
            reading.setSessionOrder(orderCount);
            reading.setSessionId(bundle.getInt("session_id"));

            db.addReading(reading);
            orderCount++;

            showReadings();
        }
    }

    public int getNextKey() {
        return db.getReadingCount() + 1;
    }


}

