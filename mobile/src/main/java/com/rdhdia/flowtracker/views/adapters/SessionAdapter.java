package com.rdhdia.flowtracker.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdhdia.flowtracker.BuildConfig;
import com.rdhdia.flowtracker.R;
import com.rdhdia.flowtracker.models.Session;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rdhdia on 06/05/2016.
 */
public class SessionAdapter  extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {

    private List<Session> items;
    private HashMap<String, Integer> counts;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.lblSessionId)
        TextView sessionId;

        @Bind(R.id.lblReadingsCount)
        TextView readingsCount;

        @Bind(R.id.layoutSession)
        LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public SessionAdapter(List<Session> items, HashMap<String, Integer> counts, Context context) {
        this.items = items;
        this.counts = counts;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_session, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Session session = items.get(position);

        holder.sessionId.setText(session.getId());

        holder.readingsCount.setText(counts.get(session.getId()) + " readings");

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(BuildConfig.APPLICATION_ID,
                        BuildConfig.APPLICATION_ID + ".activities.SessionDetailsActivity");
                intent.putExtra("session_id", session.getId());
                context.startActivity(intent);
            }
        });
    }
}
