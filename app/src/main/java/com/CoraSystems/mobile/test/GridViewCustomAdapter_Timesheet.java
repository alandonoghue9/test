package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.CoraSystems.mobile.test.Objects.ByDay;
import com.CoraSystems.mobile.test.Objects.ByDayInArray;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.ByDayGlobal;

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

    int selected;
    double hoursIn;

    View grid;

    timesheetDays parentFrag;
    int section;

    int ByDayID;

    Time today;
    Calendar calendar;

    String hoursWork;
    String h;

    public GridViewCustomAdapter_Timesheet(Context context, timesheetDays Frag, String start, clickListener buttonListener)
    {
        super(context, 0);

        this.mCallback = buttonListener;

        this.context=context;
        this.parentFrag = Frag;

        selected = 20;

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
    }

    public int getCount()
    {
        return 7;
    }

    public int item;
    double plannedHours;
    SimpleDateFormat sdf;
    String ByDayDate;
    int ByDayTaskID;

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {
        /*int ByDayIDpos = i*section;
        ByDayID = parentFrag.Days.get(ByDayIDpos).getId();
        plannedHours = parentFrag.Days.get(ByDayIDpos).getPlannedHours();
        ByDayDate = parentFrag.Days.get(ByDayIDpos).getDate();
        ByDayTaskID = parentFrag.Days.get(ByDayIDpos).gettaskId();*/

        sdf = new SimpleDateFormat("dd-MM-yyyy");

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

        item = i;

        ImageView imageView = (ImageView)grid.findViewById(R.id.dots);
        imageView.setImageResource(R.drawable.ic_action_overflow);
        TextView dateGrid = (TextView) grid.findViewById(R.id.date_GridView);

        if((mCallback.isItemSelected(i)==i)&&(mCallback.returnClicks(i)==Boolean.FALSE)){

            if(mCallback.returnClicked(i)==Boolean.FALSE) {
                ViewSwitcher switcher = (ViewSwitcher) grid.findViewById(R.id.my_switcher);
                switcher.showNext();
            }
            mCallback.changeClicked(i, Boolean.TRUE);
            for(int x=0;x<7;x++){
                mCallback.changeClicks(x, Boolean.FALSE);
            }
            mCallback.changeClicks(i, Boolean.TRUE);

            TextView hrs = (TextView) grid.findViewById(R.id.new_hrs);
            TextView dayTop = (TextView) grid.findViewById(R.id.hour);
            TextView dayShort = (TextView) grid.findViewById(R.id.hour_label);
            EditText edit = (EditText)grid.findViewById(R.id.hidden_edit_view);

            imageView.setVisibility(View.VISIBLE);
            dayTop.setVisibility(View.INVISIBLE);
            hrs.setText("hrs");
            if(i==0) {
                dayShort.setText("mon");
            }
            else if(i==1){
                dayShort.setText("tues");
            }
            else if(i==2){
                dayShort.setText("wed");
            }
            else if(i==3){
                dayShort.setText("thurs");
            }
            else if(i==4){
                dayShort.setText("fri");
            }
            else if(i==5){
                dayShort.setText("sat");
            }
            else if(i==6){
                dayShort.setText("sun");
            }
            dateGrid.setText(dayDate);

            edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                        h = v.getText().toString();
                        //hoursIn = Double.parseDouble(h);
                        /*SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                        String timestamp = s.format(new Date());

                        ByDay day = new ByDay(ByDayID, "", plannedHours, hoursIn, timestamp, ByDayDate, ByDayTaskID);

                        ByDayInArray.ByDayIn.add(day);*/

                        /*SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                        String timestamp = s.format(new Date());

                        ByDay day = new ByDay(1234, "", 8, hoursIn, timestamp, timestamp, 12345);

                        ByDayInArray.ByDayIn.add(day);*/

                        mCallback.onArticleSelected(-1);
                    }
                    return false;
                }
            });
        }

        else if(((mCallback.isItemSelected(i)==i)&&(mCallback.returnClicks(i)==Boolean.TRUE))||(mCallback.returnClicks(i)/*parentFrag.clicks[i]*/==Boolean.FALSE)) {

            if(mCallback.returnClicked(i)==Boolean.TRUE) {
                ViewSwitcher switcher = (ViewSwitcher) grid.findViewById(R.id.my_switcher);
                switcher.showPrevious();
            }
            mCallback.changeClicked(i, Boolean.FALSE);
            for(int x=0;x<7;x++) {
                mCallback.changeClicks(x, Boolean.FALSE);
            }

            TextView hrsTop = (TextView) grid.findViewById(R.id.new_hrs);
            TextView dayLetter = (TextView) grid.findViewById(R.id.day_letter);
            TextView hoursWorked = (TextView) grid.findViewById(R.id.hour);
            TextView hrs = (TextView) grid.findViewById(R.id.hour_label);

            int randomHour = ((int)(Math.random()*(8)));
            String workHours = Integer.toString(randomHour);

            if(i==6){
                hoursWorked.setText("");
                hrs.setText("");
            }

            /*double hoursInFromArray=0;
            double hoursInFromGlobal=0;

            for (int n = 0; n < ByDayInArray.ByDayIn.size(); n++) {
                if (ByDayInArray.ByDayIn.get(n).getId() == ByDayID) {
                    hoursInFromArray = ByDayInArray.ByDayIn.get(n).getHours();
                    n = ByDayInArray.ByDayIn.size() - 1;
                }
            }

            if(hoursInFromArray==0){
                for(int n=0;n< ByDayGlobal.ByDayConstantsList.size();n++)
                    if(ByDayGlobal.ByDayConstantsList.get(n).getId()==ByDayID) {
                        hoursInFromGlobal = ByDayGlobal.ByDayConstantsList.get(n).getHours();
                    }
            }

            if(hoursInFromArray>0){
                h=Double.toString(hoursInFromArray);
                hoursWorked.setText(h);
                hrs.setText("hrs");
            }
            else if(hoursInFromGlobal>0){
                h=Double.toString(hoursInFromGlobal);
                hoursWorked.setText(h);
                hrs.setText("hrs");
            }
            else{
                hoursWorked.setText("");
                hrs.setText("");
            }*/

            //comment this out
            /*else if(hoursIn>0){
                hoursWorked.setText(h);
                hrs.setText("hrs");
            }*/

            else if (randomHour==0){
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

    clickListener mCallback;

    public interface clickListener {
        public int isItemSelected(int position);
        public void changeClicks(int position, Boolean bool);
        public Boolean returnClicks(int position);
        public void changeClicked(int position, Boolean bool);
        public Boolean returnClicked(int position);
        public void onArticleSelected(int position);
        public Boolean returnView();
        public void changeView(Boolean bool);
    }
}
