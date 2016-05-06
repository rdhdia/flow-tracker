package com.rdhdia.flowtracker.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rdhdia.flowtracker.BuildConfig;
import com.rdhdia.flowtracker.R;
import com.rdhdia.flowtracker.models.Session;
import com.rdhdia.flowtracker.views.adapters.SessionAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.btnStartNewSession) FloatingActionButton newSession;
    @Bind(R.id.recyclerSessions) RecyclerView recyclerSessions;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // Create a RealmConfiguration which is to locate Realm file in package's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(HomeActivity.this).build();
        // Get a Realm instance for this thread
        realm = Realm.getInstance(realmConfig);

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

        RealmResults<Session> sessions = realm.where(Session.class).findAll();

        if ( sessions.size() > 0 ) {
            SessionAdapter adapter = new SessionAdapter(sessions, HomeActivity.this);
            recyclerSessions.setAdapter(adapter);
        } else {
            Toast.makeText(HomeActivity.this, "No sessions found", Toast.LENGTH_SHORT).show();
        }

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
