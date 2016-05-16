package com.rdhdia.flowtracker.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdhdia.flowtracker.R;
import com.rdhdia.flowtracker.models.Reading;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rdhdia on 06/05/2016.
 */
public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ViewHolder> {

    private List<Reading> items;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.lblTime) TextView time;
        @Bind(R.id.lblDate) TextView date;
        @Bind(R.id.lblValue) TextView value;
        @Bind(R.id.lblDifference) TextView difference;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ReadingAdapter(List<Reading> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_reading, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Reading reading = items.get(position);

        holder.time.setText(reading.getReadableTime());

        holder.date.setText(reading.getReadableDate());

        holder.value.setText(reading.getFlowValue());

        holder.difference.setText(String.valueOf(reading.getSessionOrder()));

    }
}
