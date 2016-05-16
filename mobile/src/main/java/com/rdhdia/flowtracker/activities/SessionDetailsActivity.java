package com.rdhdia.flowtracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rdhdia.flowtracker.R;
import com.rdhdia.flowtracker.models.Reading;
import com.rdhdia.flowtracker.utils.DatabaseHandler;
import com.rdhdia.flowtracker.views.adapters.ReadingAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SessionDetailsActivity extends AppCompatActivity {

    @Bind(R.id.recyclerReadingList)
    RecyclerView recyclerReadings;

    private DatabaseHandler db;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_details);
        ButterKnife.bind(this);
        db = new DatabaseHandler(this);
        bundle = getIntent().getExtras();
    }

    @Override
    protected void onStart() {
        super.onStart();

        recyclerReadings.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SessionDetailsActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerReadings.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int sessionId = Integer.valueOf(bundle.getString("session_id"));
        List<Reading> readings = db.getAllReadingBySessionId(sessionId);

        if ( readings.size() > 0 ) {
            ReadingAdapter adapter = new ReadingAdapter(readings, SessionDetailsActivity.this);
            recyclerReadings.setAdapter(adapter);
        } else {
            Toast.makeText(SessionDetailsActivity.this, "No readings found", Toast.LENGTH_SHORT).show();
        }

    }
}
