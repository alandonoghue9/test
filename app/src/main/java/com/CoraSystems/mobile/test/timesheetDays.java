package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.CoraSystems.mobile.test.Objects.ByDay;
import java.util.ArrayList;

public class timesheetDays extends Fragment {

    public static int section;

    GridView gridView;
    GridViewCustomAdapter_Timesheet grisViewCustomeAdapter;
    static timesheetDays fragment;
    GlobalSelectTimesheet select;
    ArrayList<ByDay> Days;

    public Boolean clicks[] = new Boolean[] {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE};

    public static timesheetDays newInstance(int sectionNumber, GlobalSelectTimesheet select, ArrayList<ByDay> Days, String startDate) {
        fragment = new timesheetDays();
        Bundle args = new Bundle();
        fragment.Days = Days;
        args.putInt("Sect", sectionNumber);
        args.putString("start", startDate);
        fragment.select=select;
        fragment.setArguments(args);
        return fragment;
    }
    String start;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        section = getArguments() != null ? getArguments().getInt("Sect") : 1;
        start = getArguments() != null ? getArguments().getString("start") : "1";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.gridview_layout, container, false);
        gridView=(GridView)rootView.findViewById(R.id.gridview);
        grisViewCustomeAdapter = new GridViewCustomAdapter_Timesheet(this.getActivity(), fragment, start);
        gridView.setAdapter(grisViewCustomeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                select.onItemSelectionChanged(i);
                grisViewCustomeAdapter.notifyDataSetChanged();
            }
        });
        return rootView;
    }
}
