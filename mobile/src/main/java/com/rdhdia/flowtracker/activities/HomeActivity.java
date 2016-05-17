package com.rdhdia.flowtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rdhdia.flowtracker.BuildConfig;
import com.rdhdia.flowtracker.R;
import com.rdhdia.flowtracker.models.Session;
import com.rdhdia.flowtracker.utils.DatabaseHandler;
import com.rdhdia.flowtracker.views.adapters.SessionAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.btnStartNewSession) FloatingActionButton newSession;
    @Bind(R.id.recyclerSessions) RecyclerView recyclerSessions;

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        db = new DatabaseHandler(this);

        recyclerSessions.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerSessions.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        newSession.setOnClickListener(new NewSessionListener());
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Session> sessions = db.getAllSession();

        HashMap<String, Integer> readingCounts = new HashMap<>();

        for ( Session session : sessions ) {
            int sessionReadings = db.getReadingCountBySessionId(Integer.valueOf(session.getId()));
            readingCounts.put(session.getId(), sessionReadings);
        }

        if ( sessions.size() > 0 ) {
            SessionAdapter adapter = new SessionAdapter(sessions, readingCounts, HomeActivity.this);
            recyclerSessions.setAdapter(adapter);
        } else {
            Toast.makeText(HomeActivity.this, "No sessions found", Toast.LENGTH_SHORT).show();
        }

    }

    private class NewSessionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int nextSessionId = db.getSessionCount() + 1;
            Intent intent = new Intent();
            intent.setClassName(BuildConfig.APPLICATION_ID,
                    BuildConfig.APPLICATION_ID + ".activities.SessionActivity");
            intent.putExtra("session_id", nextSessionId);
            startActivity(intent);
        }
    }
}
