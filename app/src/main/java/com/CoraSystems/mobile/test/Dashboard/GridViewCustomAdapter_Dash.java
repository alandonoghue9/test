package com.CoraSystems.mobile.test.Dashboard;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.CoraSystems.mobile.test.Objects.ObjectConstants.ByDayGlobal;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.DashboardVariables;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.NotSubmitted;
import com.CoraSystems.mobile.test.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat humanReadableDate = new SimpleDateFormat("MMM dd");

        try {
            calendar.setTime(sdf.parse(today.year + "-" + (today.month + 1) + "-" + today.monthDay));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_WEEK, i);
        dayDate1 = sdf.format(calendar.getTime());

        //Adding daily hours to array for overall hours/day
        for(int n=0; n<ByDayGlobal.ByDayConstantsList.size(); n++) {

            String day = ByDayGlobal.ByDayConstantsList.get(n).getDate();
            day = day.substring(0,10);

            if(dayDate1.equals(day)){
                Integer hours = (int)ByDayGlobal.ByDayConstantsList.get(n).getHours();
                Integer hoursAdd = DashboardVariables.hoursDay.get(i) + hours;
                DashboardVariables.hoursDay.set(i, hoursAdd);

                if(DashboardVariables.taskIDs.size()==0){
                    DashboardVariables.taskIDs.add(ByDayGlobal.ByDayConstantsList.get(n).gettaskId());
                }
                else {
                    for (int y = 0; y < DashboardVariables.taskIDs.size(); y++) {

                        if (DashboardVariables.taskIDs.get(y) != ByDayGlobal.ByDayConstantsList.get(n).gettaskId()) {
                            DashboardVariables.taskIDs.add(ByDayGlobal.ByDayConstantsList.get(n).gettaskId());
                            y = DashboardVariables.taskIDs.size() - 1;
                        }
                    }
                }
            }
        }

        try {
            dayDate1 = humanReadableDate.format(sdf.parse(dayDate1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dayDate1 = dayDate1.toUpperCase();
        TextView dateView = (TextView) grid.findViewById(R.id.date);
        dateView.setText(dayDate1);

        int intHour = DashboardVariables.hoursDay.get(i);
        String workHours;
        workHours = Integer.toString(DashboardVariables.hoursDay.get(i)) + " hrs";
        TextView hourView = (TextView) grid.findViewById(R.id.hours);
        hourView.setText(workHours);

        if(intHour>0){
            DashboardVariables.days++;
        }

        if (intHour==0) {
            grid.setBackgroundColor(context.getResources().getColor(R.color.cora_red));
        }
        if (intHour>6) {
            grid.setBackgroundColor(context.getResources().getColor(R.color.cora_green));
        }

        return grid;
    }
}
