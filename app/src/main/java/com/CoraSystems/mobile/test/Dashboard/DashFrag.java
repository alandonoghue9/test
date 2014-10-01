package com.CoraSystems.mobile.test.Dashboard;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.CoraSystems.mobile.test.Graph.PieGraph;
import com.CoraSystems.mobile.test.Graph.PieSlice;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.DashboardVariables;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.NotSubmitted;
import com.CoraSystems.mobile.test.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DashFrag extends Fragment {

    GridView gridView;
    GridViewCustomAdapter_Dash gridViewAdapterDash;

    private Spinner spinner1;

    String date;
    Time today;
    Calendar calendar;
    String dateNot;

    public ArrayList<String> displayList;
    String firstDay;
    int pos;

    int hours;
    int days;
    int tasks;
    Double complete;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dash_frag, container, false);
        displayList = new ArrayList<>();
        pos=0;

        spinner1 = (Spinner) view.findViewById(R.id.weeks_spinner);

        //list from global NotSubmitted list
        List<String> list = NotSubmitted.NotSubmitted;

        Date Thedate=new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for(int i=0;i<list.size();i++) {

            date = list.get(i);

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
            String dayDate7;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {
                calendar.setTime(sdf.parse(today.year + "-" + (today.month + 1) + "-" + today.monthDay));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.DAY_OF_WEEK, 0);

            dayDate1 = sdf.format(calendar.getTime());
            SimpleDateFormat humanReadableDate = new SimpleDateFormat("MMM dd");
            try {
                dayDate1 = humanReadableDate.format(sdf.parse(dayDate1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                calendar.setTime(sdf.parse(today.year + "-" + (today.month+1) + "-" + (today.monthDay + 5)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            dayDate7 = sdf.format(calendar.getTime());
            try {
                dayDate7 = humanReadableDate.format(sdf.parse(dayDate7));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            dayDate1 = dayDate1.toUpperCase();
            dayDate7 = dayDate7.toUpperCase();

            dateNot = dayDate1 + " - " + dayDate7;
            displayList.add(dateNot);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,displayList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);

        gridView=(GridView)view.findViewById(R.id.gridview);
        gridViewAdapterDash = new GridViewCustomAdapter_Dash(this.getActivity());
        gridView.setAdapter(gridViewAdapterDash);

        addListenerOnSpinnerItemSelection();

        return view;
    }

    public void loadStats(){

        for(int y=0; y<DashboardVariables.hoursDay.size(); y++) {
            DashboardVariables.hoursWeek = DashboardVariables.hoursWeek + DashboardVariables.hoursDay.get(y);
        }

        hours = (int)DashboardVariables.hoursWeek;
        TextView hoursView = (TextView) view.findViewById(R.id.hours);
        String hoursComp = Integer.toString((int)DashboardVariables.hoursWeek);
        hoursView.setText(hoursComp);

        days = ((int)((Math.random()*(4)+3)));
        TextView daysView = (TextView) view.findViewById(R.id.days);
        String daysComp = Integer.toString(DashboardVariables.days);
        daysView.setText(daysComp);

        tasks = ((int)(Math.random()*(12)));
        TextView tasksView = (TextView) view.findViewById(R.id.tasks);
        String tasksComp = Integer.toString(DashboardVariables.taskIDs.size());
        tasksView.setText(tasksComp);

        complete = (hours/40.00);
        complete = complete*100;

        PieGraph pg = (PieGraph)view.findViewById(R.id.graph);
        pg.removeSlices();

        if(complete<60) {
            PieSlice slice = new PieSlice();
            slice.setColor(Color.parseColor("#ffffff"));
            slice.setValue(100 - complete.intValue());
            pg.addSlice(slice);

            slice = new PieSlice();
            slice.setColor(this.getResources().getColor(R.color.cora_blue));
            slice.setValue(complete.intValue());
            pg.addSlice(slice);
        }
        else if((complete>=60)&&(complete<=115)){
            PieSlice slice = new PieSlice();
            slice.setColor(Color.parseColor("#ffffff"));
            slice.setValue(0);
            pg.addSlice(slice);

            slice = new PieSlice();
            slice.setColor(this.getResources().getColor(R.color.cora_green));
            slice.setValue(100);
            pg.addSlice(slice);
        }
        else if(complete>115){
            PieSlice slice = new PieSlice();
            slice.setColor(Color.parseColor("#ffffff"));
            slice.setValue(0);
            pg.addSlice(slice);

            slice = new PieSlice();
            slice.setColor(this.getResources().getColor(R.color.cora_red));
            slice.setValue(100);
            pg.addSlice(slice);
        }

        TextView percentView = (TextView) view.findViewById(R.id.percent);
        int percentageComplete=(complete.intValue());
        String percentComp = (Integer.toString(percentageComplete)+"%");
        percentView.setText(percentComp);

        for(int n=0; n<DashboardVariables.hoursDay.size(); n++) {
            DashboardVariables.hoursDay.set(n, 0);
        }
        DashboardVariables.hoursWeek = 0;
        DashboardVariables.tasks = 0;
        DashboardVariables.days = 0;
        DashboardVariables.taskIDs = new ArrayList<>();
    }

    public void addListenerOnSpinnerItemSelection(){
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            pos = arg2;
            NotSubmitted.i=pos;

            gridViewAdapterDash.notifyDataSetChanged();

            loadStats();

        }});

    }
}
