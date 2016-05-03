package com.rdhdia.flowtracker.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.rdhdia.flowtracker.BuildConfig;
import com.rdhdia.flowtracker.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.btnStartNewSession) FloatingActionButton newSession;
    @Bind(R.id.recyclerSessions) RecyclerView recyclerSessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

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

    private class NewSessionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClassName(BuildConfig.APPLICATION_ID,
                    BuildConfig.APPLICATION_ID + ".activities.SessionActivity");
            startActivity(intent);
        }
    }
}
