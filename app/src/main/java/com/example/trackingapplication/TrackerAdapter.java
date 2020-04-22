package com.example.trackingapplication;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

import java.util.ArrayList;

public class TrackerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Tracker> trackersList;
    private TextView phone, name;

    public TrackerAdapter(Context context, ArrayList<Tracker> trackersList) {
        this.context = context;
        this.trackersList = trackersList;
    }
    @Override
    public int getCount() {
        return trackersList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.tracker_views, parent, false);
        phone = convertView.findViewById(R.id.phone_number);
        name = convertView.findViewById(R.id.name_text_view);
        name.setText(" " + trackersList.get(position).getTrackerName());
        phone.setText(" " + trackersList.get(position).getPhoneNumber());
        return convertView;
    }
}