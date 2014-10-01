package com.CoraSystems.mobile.test.Timesheet;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.NumberPicker;

import com.CoraSystems.mobile.test.Objects.ByDay;
import com.CoraSystems.mobile.test.R;

import java.util.ArrayList;

public class timesheetDays extends Fragment /*implements GridViewCustomAdapter_Timesheet.clickListener*/ {

    /*FRAGMENT FOR EACH INDIVIDUAL DAY OF TASK (set up using GrudViewCustomAdapter_Timesheet)*/

    public static int section;

    GridView gridView;
    GridViewCustomAdapter_Timesheet grisViewCustomeAdapter;
    static timesheetDays fragment;
    public static ArrayList<ByDay> ByDayList;

    /*int selected;
    public Boolean viewBool;

    public Boolean clicks[];
    public Boolean clicked[];*/

    public static timesheetDays newInstance(int sectionNumber, ArrayList<ByDay> Days, String startDate) {
        fragment = new timesheetDays();
        Bundle args = new Bundle();
        args.putParcelableArrayList("Days", Days);
        args.putInt("Sect", sectionNumber);
        args.putString("start", startDate);
        fragment.setArguments(args);
        return fragment;
    }
    String start;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        section=bundle.getInt("Sect");
        ByDayList=new ArrayList<>();
        start=bundle.getString("start");
        ByDayList=bundle.getParcelableArrayList("Days");

        Log.i("Test for Array", Integer.toString(ByDayList.size()));

        //For selection layout
        //selected = -1;
        //clicks = new Boolean[] {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE};
        //clicked =new Boolean[] {Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE};
        //viewBool = Boolean.FALSE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.gridview_layout, container, false);
        gridView=(GridView)rootView.findViewById(R.id.gridview);
        grisViewCustomeAdapter = new GridViewCustomAdapter_Timesheet(this.getActivity(), fragment, start/*, this*/);
        gridView.setAdapter(grisViewCustomeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //onArticleSelected(i);
                if(i!=6) {
                    show(i);
                }
                //grisViewCustomeAdapter.notifyDataSetChanged();
            }
        });
        return rootView;
    }

    public void show(int i){
        //depending on 'i' can set initial values to correspond to day selected
        final Dialog d = new Dialog(getActivity());
        d.setTitle("Thurs, 20 Sep 2014");
        d.setContentView(R.layout.timesheet_data_in);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(8);
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                grisViewCustomeAdapter.notifyDataSetChanged();
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }

    /**  SELECTOR FOR SELECTION VIEW  **/

 /*   public int isItemSelected(int position){
        if(position!=selected){
            return -1;
        }
        return selected;
    }

    public void onArticleSelected(int position) {
        selected = position;
    }

    public void changeClicks(int position, Boolean bool) {
        clicks[position] = bool;
    }
    public void changeClicked(int position, Boolean bool) {
        clicked[position] = bool;
    }

    public Boolean returnClicks(int position) {
        return clicks[position];
    }
    public Boolean returnClicked(int position) {
        return clicked[position];
    }
    public Boolean returnView() {
        return viewBool;
    }
    public void changeView(Boolean bool) {
        viewBool = bool;
    }*/
}
