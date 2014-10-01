package com.CoraSystems.mobile.test.Timesheet;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.CoraSystems.mobile.test.Objects.ByDay;
import com.CoraSystems.mobile.test.Objects.ByDayInArray;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.ByDayGlobal;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.TaskGlobal;
import com.CoraSystems.mobile.test.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class GridViewCustomAdapter_Timesheet extends ArrayAdapter
{
    //int selected;

    View grid;

    Context context;
    String dayLabel[]=new String[] {"M","T","W","T","F","S","S"};
    timesheetDays parentFrag;
    int section;

    String hoursWork;
    String h;

    Time today;
    Calendar calendar;

    ArrayList<ByDay> Days;
    int ByDayID;
    String startDate;
    DateFormat formatter;

    public GridViewCustomAdapter_Timesheet(Context context, timesheetDays Frag, String start/*, clickListener buttonListener*/)
    {
        super(context, 0);

        //this.mCallback = buttonListener;

        this.context=context;
        this.parentFrag = Frag;

        this.startDate = start;
        this.Days = Frag.ByDayList;

        //selected = 20;

        Date date=new Date();

        Log.i("start test", start);

        h="0";

        formatter = new SimpleDateFormat("yyyy-MM-dd");

        int randomHour = ((int)(Math.random()*(8)));
        hoursWork = Integer.toString(randomHour);

        try {
            date = formatter.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        today = new Time();
        today.set(calendar.getTimeInMillis());

        section = Frag.section;
    }

    public int getCount()
    {
        return 7;
    }

    public int item;

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {

        try {
            calendar.setTime(formatter.parse(today.year + "-" + today.month + "-" + today.monthDay + (section*7)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.add(Calendar.DAY_OF_WEEK, i);

        String dayDate = formatter.format(calendar.getTime());

        try{
            dayDate = formatter.format(formatter.parse(dayDate));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        ByDayID=0;

        if(Days.size()>0) {
            for (int c = 0; c < Days.size(); c++) {
                String ByDayDate = Days.get(c).getDate();
                ByDayDate = ByDayDate.substring(0,10);
                Log.i("parent Layout ByDayDate", ByDayDate);
                Log.i("parent Layout DateByDay_current", dayDate);
                if (ByDayDate == dayDate) {
                    ByDayID = ByDayGlobal.ByDayConstantsList.get(c).getId();
                    Log.i("parent Layout", "GOT IT!");
                    c = TaskGlobal.task.size() - 1;
                }
            }
        }
        else Log.i("no days", "no days");

        SimpleDateFormat humanReadableDate = new SimpleDateFormat("MMM dd");
        try{
            dayDate = humanReadableDate.format(formatter.parse(dayDate));
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

       /* if((mCallback.isItemSelected(i)==i)&&(mCallback.returnClicks(i)==Boolean.FALSE)){

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
                        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                        String timestamp = s.format(new Date());

                        ByDay day = new ByDay(ByDayID, "", plannedHours, hoursIn, timestamp, ByDayDate, ByDayTaskID);

                        ByDayInArray.ByDayIn.add(day);

                        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                        String timestamp = s.format(new Date());

                        ByDay day = new ByDay(1234, "", 8, hoursIn, timestamp, timestamp, 12345);

                        ByDayInArray.ByDayIn.add(day);

                        mCallback.onArticleSelected(-1);
                    }
                    return false;
                }
            });
        }*/

        //else if(((mCallback.isItemSelected(i)==i)&&(mCallback.returnClicks(i)==Boolean.TRUE))||(mCallback.returnClicks(i)/*parentFrag.clicks[i]*/==Boolean.FALSE)) {

            /*if(mCallback.returnClicked(i)==Boolean.TRUE) {
                ViewSwitcher switcher = (ViewSwitcher) grid.findViewById(R.id.my_switcher);
                switcher.showPrevious();
            }
            mCallback.changeClicked(i, Boolean.FALSE);
            for(int x=0;x<7;x++) {
                mCallback.changeClicks(x, Boolean.FALSE);
            }*/

            TextView hrsTop = (TextView) grid.findViewById(R.id.new_hrs);
            TextView dayLetter = (TextView) grid.findViewById(R.id.day_letter);
            TextView hoursWorked = (TextView) grid.findViewById(R.id.hour);
            TextView hrs = (TextView) grid.findViewById(R.id.hour_label);

            int randomHour = ((int)(Math.random()*(8)));

            if(i==6){
                hoursWorked.setText("");
                hrs.setText("");
            }

            double hoursInFromArray=0;
            double hoursInFromGlobal=0;

            if(ByDayInArray.ByDayIn.size()>0) {
                for (int n = 0; n < ByDayInArray.ByDayIn.size(); n++) {
                    if (ByDayInArray.ByDayIn.get(n).gettaskId() == ByDayID) {
                        hoursInFromArray = ByDayInArray.ByDayIn.get(n).getHours();
                        n = ByDayInArray.ByDayIn.size() - 1;
                    }
                }
            }

            if((hoursInFromArray==0)&&(ByDayGlobal.ByDayConstantsList.size()>0)){
                for(int n=0;n< ByDayGlobal.ByDayConstantsList.size();n++)
                    if(ByDayGlobal.ByDayConstantsList.get(n).gettaskId()==ByDayID) {
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
            }

            //comment this out
            /*else if(hoursIn>0){
                hoursWorked.setText(h);
                hrs.setText("hrs");
            }*/
/*
            else if (randomHour==0){
                hoursWorked.setText("");
                hrs.setText("");
            }
            else {
                hoursWorked.setText(workHours);
                hrs.setText("hrs");
            }*/

        //These variables used for selection view
            imageView.setVisibility(View.INVISIBLE);
            hrsTop.setVisibility(View.INVISIBLE);

            dayLetter.setText(dayLabel[i]);
            dateGrid.setText(dayDate);

//Using random to demonstrate colours, randomHour will be hoursInFromGlobal, hoursInFromArray or '0'
            if (randomHour>5) {
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_green));
            }
            else if ((randomHour < 6)&&(randomHour>2)) {
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_red));
            }
            else if (randomHour <3) {
                grid.setBackgroundColor(context.getResources().getColor(R.color.cora_blue));
            }
        //for Sunday
            if (i==6){
                grid.setBackgroundColor(context.getResources().getColor(R.color.background_grey));
            }
        //}
        return grid;
    }


    /** INTERFACE FOR SELECTION **/
/*    clickListener mCallback;

    public interface clickListener {
        public int isItemSelected(int position);
        public void changeClicks(int position, Boolean bool);
        public Boolean returnClicks(int position);
        public void changeClicked(int position, Boolean bool);
        public Boolean returnClicked(int position);
        public void onArticleSelected(int position);
        public Boolean returnView();
        public void changeView(Boolean bool);
    }*/
}
