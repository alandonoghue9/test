package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.CoraSystems.mobile.test.Graph.PieGraph;
import com.CoraSystems.mobile.test.Graph.PieSlice;
import com.CoraSystems.mobile.test.Objects.ByDay;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.ByDayGlobal;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.NotSubmitted;
import com.CoraSystems.mobile.test.Objects.Task;

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

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dash_frag, container, false);
        displayList = new ArrayList<>();
        pos=0;

        spinner1 = (Spinner) view.findViewById(R.id.weeks_spinner);
        List<String> list = NotSubmitted.NotSubmitted; //new ArrayList<>();
        /*list.add("21 July - 27 July");
        list.add("28 July - 3 August");
        list.add("4 August - 10 August");
        list.add("11 August - 17 August");
        list.add("1 September - 7 September");*/

        Date Thedate=new Date();
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

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

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            try {
                calendar.setTime(sdf.parse(today.monthDay + "-" + today.month + "-" + today.year));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.DAY_OF_WEEK, 0);

            dayDate1 = sdf.format(calendar.getTime());
            SimpleDateFormat humanReadableDate = new SimpleDateFormat("MMM dd");
            try {
                dayDate1 = humanReadableDate.format(sdf.parse(dayDate1));
                Log.v("blah", dayDate1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                calendar.setTime(sdf.parse(today.monthDay + 5 + "-" + today.month + "-" + today.year));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            dayDate7 = sdf.format(calendar.getTime());
            try {
                dayDate7 = humanReadableDate.format(sdf.parse(dayDate7));
                Log.v("blah", dayDate7);
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

        //LIST OF DAYS WITHIN WEEK
        /*ArrayList<ByDay> tasks = new ArrayList<>();
        for(int i = 0; i< ByDayGlobal.ByDayConstantsList.size();i++){
            if(ByDayGlobal.ByDayConstantsList.get(i).getDate()==date){
                tasks.add(ByDayGlobal.ByDayConstantsList.get(i));
            }
        }*/
        firstDay = spinner1.getSelectedItem().toString();
        for(int i=0;i<displayList.size();i++){
            if(firstDay==displayList.get(i)){
                pos = i;
            }
        }
        firstDay = date.substring(0, 6);

        gridView=(GridView)view.findViewById(R.id.gridview);
        gridViewAdapterDash = new GridViewCustomAdapter_Dash(this.getActivity(), pos);
        gridView.setAdapter(gridViewAdapterDash);

        addListenerOnSpinnerItemSelection();

        loadGraph();

        return view;
    }

    public void loadGraph(){
        int complete = 60;

        PieGraph pg = (PieGraph)view.findViewById(R.id.graph);
        PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor("#ffffff"));
        slice.setValue(100-complete);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#1da9e1"));
        slice.setValue(complete);
        pg.addSlice(slice);
    }

    public void addListenerOnSpinnerItemSelection(){
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
        int arg2, long arg3) {
            // TODO Auto-generated method stub
            String s = spinner1.getItemAtPosition(arg2).toString();
            //list.add(s);
            gridViewAdapterDash.notifyDataSetChanged();
        }});
        /*date = spinner1.getSelectedItem().toString();
        firstDay = spinner1.getSelectedItem().toString();
        for(int i=0;i<displayList.size();i++){
            if(firstDay==displayList.get(i)){
                pos = i;
            }
        }*/
        //gridViewAdapterDash = new GridViewCustomAdapter_Dash(this.getActivity(), pos);
        //gridView.setAdapter(gridViewAdapterDash);
        //gridViewAdapterDash.notifyDataSetChanged();

        /*date = date.substring(0, 6);

        //ARRANGE INTO CORRECT FORMAT
        //date = date.substring(0, 7);
        Date Thedate=new Date();
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
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

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            calendar.setTime(sdf.parse(today.monthDay + "-" + today.month + "-" + today.year));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_WEEK, 0);

            dayDate1 = sdf.format(calendar.getTime());
            SimpleDateFormat humanReadableDate = new SimpleDateFormat("MMM dd");
            try {
                dayDate1 = humanReadableDate.format(sdf.parse(dayDate1));
                Log.v("blah", dayDate1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                calendar.setTime(sdf.parse(today.monthDay + 7 + "-" + today.month + "-" + today.year));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        dayDate7 = sdf.format(calendar.getTime());
        try {
            dayDate7 = humanReadableDate.format(sdf.parse(dayDate7));
            Log.v("blah", dayDate7);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }
}
