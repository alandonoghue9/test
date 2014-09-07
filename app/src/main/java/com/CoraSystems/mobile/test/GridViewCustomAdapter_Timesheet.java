package com.CoraSystems.mobile.test;

import android.content.Context;

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


public class GridViewCustomAdapter_Timesheet extends ArrayAdapter
{
    Context context;
    String text[]=new String[] {"M","T","W","T","F","S","S"};


    ItemSelectionInterface selectionInterface;

    View grid;
    boolean started = false;
    int clicked[]=new int[] {0,0,0,0,0,0,0};

    timesheetDays parentFrag;

    public GridViewCustomAdapter_Timesheet(Context context, timesheetDays Frag)
    {
        super(context, 0);
        this.context=context;

        this.parentFrag = Frag;

        selectionInterface=Frag.selectionInterface;
    }

    public int getCount()
    {
        return 7;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent)
    {

      Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();

        try {
            c.setTime(sdf.parse(today.monthDay+"-"+today.month+1+"-"+today.year));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, i);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        String outputDate = sdf.format(c.getTime());
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
        if((selectionInterface.getSelectedItemOnFragment(i) == i)&& (parentFrag.clicks[i]==Boolean.FALSE)){
            for(int x=0;x<7;x++){
                parentFrag.clicks[x]=Boolean.FALSE;
            }
            parentFrag.clicks[i]=Boolean.TRUE;

            imageView.setVisibility(View.VISIBLE);

            if(clicked[i]==0) {
                ViewSwitcher switcher = (ViewSwitcher) grid.findViewById(R.id.my_switcher);
                switcher.showNext();
            }

            TextView hrs = (TextView) grid.findViewById(R.id.new_hrs);
            TextView day = (TextView) grid.findViewById(R.id.hour);
            TextView day1 = (TextView) grid.findViewById(R.id.hour_label);


            day.setText("");
            hrs.setText("hrs");
            day1.setText("wed");
            dateGrid.setText(outputDate);

            clicked[i]=1;
        }
        else if(((selectionInterface.getSelectedItemOnFragment(i) == i) && (parentFrag.clicks[i]==Boolean.TRUE))||(parentFrag.clicks[i]==Boolean.FALSE)){
            for(int y=0;y<7;y++){
                parentFrag.clicks[y]=Boolean.FALSE;
            }

            imageView.setVisibility(View.INVISIBLE);

            if(clicked[i]==1) {
                ViewSwitcher switcher = (ViewSwitcher) grid.findViewById(R.id.my_switcher);
                switcher.showPrevious();
            }
            clicked[i]=0;

            TextView viewtext = (TextView) grid.findViewById(R.id.new_hrs);
            TextView textView = (TextView) grid.findViewById(R.id.day_letter);
            TextView day = (TextView) grid.findViewById(R.id.hour);
            TextView day1 = (TextView) grid.findViewById(R.id.hour_label);

            textView.setText(text[i]);
            day.setText("8");
            viewtext.setText("");
            day1.setText("hrs");
            dateGrid.setText(outputDate);
            if(i==1) {
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_green));
            }
            else if(i==3){
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_red));
            }
            else{
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_blue));
            }
        }

        return grid;
    }


}
