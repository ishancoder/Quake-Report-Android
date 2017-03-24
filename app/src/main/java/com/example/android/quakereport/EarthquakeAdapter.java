package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private ArrayList<Earthquake> mEarthquakeData;
    private Context mContext;

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> data){
        super(context, R.layout.earthquake_list_item);
        this.mEarthquakeData = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mEarthquakeData.size();
    }

    @Override
    public Earthquake getItem(int position) {
        return mEarthquakeData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewHolder viewHolder;

        Earthquake quake = mEarthquakeData.get(position);

        String location = quake.getPlace();
        String formattedLocations[]  = formatLocation(location);

        Date date = new Date(quake.getTime());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String formattedDate = dateFormatter.format(date);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
        String formattedTime = timeFormatter.format(date);

        DecimalFormat decimalFormatter = new DecimalFormat("0.0");
        String formattedMag = decimalFormatter.format(quake.getMagnitude());

        if(convertView != null){
            viewHolder = (ViewHolder)convertView.getTag();

        } else {
            convertView = inflater.inflate(R.layout.earthquake_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.magTextView = (TextView) convertView.findViewById(R.id.magnitude);
            viewHolder.placeTextView = (TextView) convertView.findViewById(R.id.primary_location);
            viewHolder.timeTextView = (TextView) convertView.findViewById(R.id.time);
            viewHolder.dateTextView = (TextView) convertView.findViewById(R.id.date);
            viewHolder.directionTextView = (TextView) convertView.findViewById(R.id.location_offset);

            convertView.setTag(viewHolder);
        }


        viewHolder.magTextView.setText(formattedMag);
        viewHolder.dateTextView.setText(formattedDate);
        viewHolder.timeTextView.setText(formattedTime);
        viewHolder.directionTextView.setText(formattedLocations[0]);
        viewHolder.placeTextView.setText(formattedLocations[1]);

        GradientDrawable gradientDrawable = (GradientDrawable) viewHolder.magTextView.getBackground();
        gradientDrawable.setColor(getMagnitudeColor(quake.getMagnitude()));

        return convertView;
    }

    public static class ViewHolder{
        public TextView magTextView, placeTextView, directionTextView, timeTextView, dateTextView;
    }

    private String[] formatLocation(String location){
        if(location.contains("of")){
            String[] locations = location.split(" of ");
            locations[0] += " of";
            return locations;
        }
        return new String[]{"Near the", location};
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
