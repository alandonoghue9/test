package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.CoraSystems.mobile.test.Objects.ByDay;
import java.util.ArrayList;

public class timesheetDays extends Fragment implements GridViewCustomAdapter_Timesheet.clickListener {

    public static int section;

    GridView gridView;
    GridViewCustomAdapter_Timesheet grisViewCustomeAdapter;
    static timesheetDays fragment;
    ArrayList<ByDay> Days;
    int selected;
    public Boolean viewBool;

    public Boolean clicks[];
    public Boolean clicked[];

    public static timesheetDays newInstance(int sectionNumber, ArrayList<ByDay> Days, String startDate) {
        fragment = new timesheetDays();
        Bundle args = new Bundle();
        fragment.Days = Days;
        args.putInt("Sect", sectionNumber);
        args.putString("start", startDate);
        fragment.setArguments(args);
        return fragment;
    }
    String start;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        section = getArguments() != null ? getArguments().getInt("Sect") : 1;
        start = getArguments() != null ? getArguments().getString("start") : "1";
        selected = 20;
        clicks = new Boolean[] {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE};
        clicked =new Boolean[] {Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE};
        viewBool = Boolean.FALSE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.gridview_layout, container, false);
        gridView=(GridView)rootView.findViewById(R.id.gridview);
        grisViewCustomeAdapter = new GridViewCustomAdapter_Timesheet(this.getActivity(), fragment, start, this);
        gridView.setAdapter(grisViewCustomeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                onArticleSelected(i);
                grisViewCustomeAdapter.notifyDataSetChanged();
            }
        });
        return rootView;
    }

    public int isItemSelected(int position){
        if(position!=selected){
            return 20;
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
    }
}
