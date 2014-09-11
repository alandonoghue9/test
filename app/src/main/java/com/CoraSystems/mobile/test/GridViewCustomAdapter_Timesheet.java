package com.CoraSystems.mobile.test;

import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class GridViewCustomAdapter_Timesheet extends ArrayAdapter
{
    Context context;
    String text[]=new String[] {"M","T","W","T","F","S","S"};

    ItemSelectionInterface selectionInterface;

    Boolean clicked[]=new Boolean[] {Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE};

    View grid;

    timesheetDays parentFrag;
    int section;

    Calendar w;

    public GridViewCustomAdapter_Timesheet(Context context, timesheetDays Frag)
    {
        super(context, 0);

        this.context=context;
        this.parentFrag = Frag;

        w = Calendar.getInstance();
        w.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        section = Frag.section;
        //w.add(Calendar.DATE, (section*7));

        selectionInterface=Frag.selectionInterface;
    }

    public int getCount()
    {
        return 7;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            w.setTime(sdf.parse(today.monthDay + (section*7) + "-" + today.month + 1 + "-" + today.year));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        w.add(Calendar.DAY_OF_WEEK, i);

        String outputDate = sdf.format(w.getTime());
        SimpleDateFormat humanReadableDate = new SimpleDateFormat("MMM dd");
        try{
            outputDate = humanReadableDate.format(sdf.parse(outputDate));}
        catch (ParseException e) {
            e.printStackTrace();
        }

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = inflater.inflate(R.layout.day, parent, false);
        } else {
            grid = convertView;
        }

        ImageView imageView = (ImageView)grid.findViewById(R.id.dots);
        imageView.setImageResource(R.drawable.ic_action_overflow);
        TextView dateGrid = (TextView) grid.findViewById(R.id.date_GridView);

        if((parentFrag.select == i) && (parentFrag.clicks[i]==Boolean.FALSE)){

            for(int x=0;x<7;x++){
                parentFrag.clicks[x]=Boolean.FALSE;
            }
            parentFrag.clicks[i]=Boolean.TRUE;

            imageView.setVisibility(View.VISIBLE);

            if(clicked[i]==Boolean.FALSE) {
                ViewSwitcher switcher = (ViewSwitcher) grid.findViewById(R.id.my_switcher);
                switcher.showNext();
            }
            clicked[i]=Boolean.TRUE;

            TextView hrs = (TextView) grid.findViewById(R.id.new_hrs);
            TextView day = (TextView) grid.findViewById(R.id.hour);
            TextView day1 = (TextView) grid.findViewById(R.id.hour_label);

            day.setText("");
            hrs.setText("hrs");
            day1.setText("wed");
            dateGrid.setText(outputDate);
        }
        else if(((parentFrag.select == i) && (parentFrag.clicks[i]==Boolean.TRUE))||(parentFrag.clicks[i]==Boolean.FALSE)) {
            for (int y = 0; y < 7; y++) {
                parentFrag.clicks[y] = Boolean.FALSE;
            }

            imageView.setVisibility(View.INVISIBLE);

            if (clicked[i] == Boolean.TRUE) {
                ViewSwitcher switcher = (ViewSwitcher) grid.findViewById(R.id.my_switcher);
                switcher.showPrevious();
            }
            clicked[i] = Boolean.FALSE;

            TextView viewtext = (TextView) grid.findViewById(R.id.new_hrs);
            TextView textView = (TextView) grid.findViewById(R.id.day_letter);
            TextView day = (TextView) grid.findViewById(R.id.hour);
            TextView day1 = (TextView) grid.findViewById(R.id.hour_label);

            int randomHour = ((int)(Math.random()*(8)));
            String sectionNum = Integer.toString(randomHour);
            day1.setText("hrs");
            if(i==6){
                sectionNum="";
                day1.setText("");
            }

            textView.setText(text[i]);
            day.setText(sectionNum);
            viewtext.setText("");
            dateGrid.setText(outputDate);

            if (randomHour>5) {
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_green));
            } else if ((randomHour < 6)&&(randomHour>2)) {
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_red));
            } else if (randomHour <3) {
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_blue));
            }
            if (i==6){
                grid.setBackgroundColor(context.getResources().getColor(R.color.background_grey));
            }
        }
        return grid;
    }
}
