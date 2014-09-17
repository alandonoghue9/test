package com.CoraSystems.mobile.test;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.CoraSystems.mobile.test.Objects.ObjectConstants.NotSubmitted;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GridViewCustomAdapter_Dash extends ArrayAdapter
{
    Context context;
    String text[]=new String[] {"M","T","W","T","F","S","S"};
    String date;
    int firstDay;
    Time today;
    Calendar calendar;

    View grid;

    public GridViewCustomAdapter_Dash(Context context)
    {
        super(context, 0);
        this.context=context;
    }

    public int getCount()
    {
        return 7;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent)
    {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = inflater.inflate(R.layout.submit_day, parent, false);
        } else {
            grid = convertView;
        }

        this.firstDay = NotSubmitted.i;

        TextView textView = (TextView) grid.findViewById(R.id.day);
        textView.setText(text[i]);

        Date Thedate=new Date();
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        date = NotSubmitted.NotSubmitted.get(firstDay);

            try {
                Thedate = formatter.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar = Calendar.getInstance();
            calendar.setTime(Thedate);

            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

            today = new Time();
            today.set(calendar.getTimeInMillis());
            String dayDate1;

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            SimpleDateFormat humanReadableDate = new SimpleDateFormat("MMM dd");
        //for(int x = 0;x<7;x++) {
            try {
                calendar.setTime(sdf.parse(today.monthDay + "-" + today.month + "-" + today.year));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.DAY_OF_WEEK, i);
            dayDate1 = sdf.format(calendar.getTime());
            try {
                dayDate1 = humanReadableDate.format(sdf.parse(dayDate1));
                Log.v("blah", dayDate1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        dayDate1 = dayDate1.toUpperCase();
        TextView dateView = (TextView) grid.findViewById(R.id.date);
        dateView.setText(dayDate1);
        //}

        int randomHour = ((int)(Math.random()*(8)));
        String workHours = Integer.toString(randomHour);
        workHours = workHours + " hrs";
        TextView hourView = (TextView) grid.findViewById(R.id.hours);
        hourView.setText(workHours);

        if (randomHour==0) {
            grid.setBackgroundColor(context.getResources().getColor(R.color.cora_red));
        }
        if (randomHour>6) {
            grid.setBackgroundColor(context.getResources().getColor(R.color.cora_green));
        }

        return grid;
    }
}
