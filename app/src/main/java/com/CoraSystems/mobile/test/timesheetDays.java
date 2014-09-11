package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class timesheetDays extends Fragment {

    public static int section;

    public int select=-1;

    ItemSelectionInterface selectionInterface;

    GridView gridView;
    GridViewCustomAdapter_Timesheet grisViewCustomeAdapter;
    static timesheetDays fragment;

    public Boolean clicks[] = new Boolean[] {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE};

    public static timesheetDays newInstance(int sectionNumber) {
        fragment = new timesheetDays();
        Bundle args = new Bundle();
        args.putInt("Sect", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        section = getArguments() != null ? getArguments().getInt("Sect") : 1;
        select = -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.gridview_layout, container, false);
        gridView=(GridView)rootView.findViewById(R.id.gridview);
        grisViewCustomeAdapter = new GridViewCustomAdapter_Timesheet(this.getActivity(), fragment);
        gridView.setAdapter(grisViewCustomeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                select = i;
                grisViewCustomeAdapter.notifyDataSetChanged();
            }
        });
        return rootView;
    }
}
