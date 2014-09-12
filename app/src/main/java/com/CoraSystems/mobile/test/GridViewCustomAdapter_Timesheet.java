package com.CoraSystems.mobile.test;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class GridViewCustomAdapter_Timesheet extends ArrayAdapter
{
    Context context;
    String dayLabel[]=new String[] {"M","T","W","T","F","S","S"};

    Boolean clicked[]=new Boolean[] {Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE};

    View grid;

    timesheetDays parentFrag;
    int section;
    GlobalSelectTimesheet select;

    Time today;
    Calendar calendar;

    String hoursWork;
    String h;

    public GridViewCustomAdapter_Timesheet(Context context, timesheetDays Frag, String start)
    {
        super(context, 0);

        this.context=context;
        this.parentFrag = Frag;

        Date date=new Date();

        h="0";

        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

        int randomHour = ((int)(Math.random()*(8)));
        hoursWork = Integer.toString(randomHour);

        try {
            date = formatter.parse("09-20-2014");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        today = new Time();
        today.set(calendar.getTimeInMillis());

        TimeZone tz = TimeZone.getDefault();
        int offsetInMillis = tz.getOffset(calendar.getTimeInMillis());
        long millis = calendar.getTimeInMillis();
        //millis -= offsetInMillis;
        String test = Long.toString(millis);
        Log.v("timeTest", test);

        section = Frag.section;

        this.select = Frag.select;
    }

    public int getCount()
    {
        return 7;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            calendar.setTime(sdf.parse(today.monthDay + (section*7) + "-" + today.month + 1 + "-" + today.year));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_WEEK, i);

        String dayDate = sdf.format(calendar.getTime());
        SimpleDateFormat humanReadableDate = new SimpleDateFormat("MMM dd");
        try{
            dayDate = humanReadableDate.format(sdf.parse(dayDate));
        }
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

        if((select.getSelectedItemOnFragment(i)==i)&&(parentFrag.clicks[i]==Boolean.FALSE)){

            for(int x=0;x<7;x++){
                parentFrag.clicks[x]=Boolean.FALSE;
            }
            parentFrag.clicks[i]=Boolean.TRUE;

            if(clicked[i]==Boolean.FALSE) {
                ViewSwitcher switcher = (ViewSwitcher) grid.findViewById(R.id.my_switcher);
                switcher.showNext();
            }
            clicked[i]=Boolean.TRUE;

            TextView hrs = (TextView) grid.findViewById(R.id.new_hrs);
            TextView dayTop = (TextView) grid.findViewById(R.id.hour);
            TextView dayShort = (TextView) grid.findViewById(R.id.hour_label);
            EditText edit = (EditText)grid.findViewById(R.id.hidden_edit_view);

            h = edit.getText().toString();

            imageView.setVisibility(View.VISIBLE);
            dayTop.setVisibility(View.INVISIBLE);
            hrs.setText("hrs");
            dayShort.setText("wed");
            dateGrid.setText(dayDate);
        }

        else if(((select.getSelectedItemOnFragment(i)==i)&&(parentFrag.clicks[i]==Boolean.TRUE))||(parentFrag.clicks[i]==Boolean.FALSE)) {
            for (int y = 0; y < 7; y++) {
                parentFrag.clicks[y] = Boolean.FALSE;
            }

            if (clicked[i] == Boolean.TRUE) {
                ViewSwitcher switcher = (ViewSwitcher) grid.findViewById(R.id.my_switcher);
                switcher.showPrevious();
            }
            clicked[i] = Boolean.FALSE;

            TextView hrsTop = (TextView) grid.findViewById(R.id.new_hrs);
            TextView dayLetter = (TextView) grid.findViewById(R.id.day_letter);
            TextView hoursWorked = (TextView) grid.findViewById(R.id.hour);
            TextView hrs = (TextView) grid.findViewById(R.id.hour_label);

            int randomHour = ((int)(Math.random()*(8)));
            String workHours = Integer.toString(randomHour);

            if(i==6){
                hoursWork="";
                hrs.setText("");
            }

            if(randomHour==0){
                hoursWorked.setText("");
                hrs.setText("");
            }
            else {
                hoursWorked.setText(workHours);
                hrs.setText("hrs");
            }

            imageView.setVisibility(View.INVISIBLE);
            dayLetter.setText(dayLabel[i]);
            hrsTop.setVisibility(View.INVISIBLE);
            dateGrid.setText(dayDate);

            if (randomHour>5) {
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_green));
            }
            else if ((randomHour < 6)&&(randomHour>2)) {
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_red));
            }
            else if (randomHour <3) {
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_blue));
            }
            if (i==6){
                grid.setBackgroundColor(context.getResources().getColor(R.color.background_grey));
            }
        }
        return grid;
    }
}
