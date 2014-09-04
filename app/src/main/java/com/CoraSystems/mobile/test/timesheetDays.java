package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class timesheetDays extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static int section;

    ItemSelectionInterface selectionInterface;

    GridView gridView;
    GridViewCustomAdapter_Timesheet grisViewCustomeAdapter;
    static timesheetDays fragment;

    public Boolean clicks[] = new Boolean[] {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE};

    public static timesheetDays newInstance(int sectionNumber, ItemSelectionInterface selectionInterface) {
        fragment = new timesheetDays();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        section = sectionNumber;
        fragment.setArguments(args);
        fragment.selectionInterface=selectionInterface;
        return fragment;
    }

    public timesheetDays() {
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
                selectionInterface.onItemSelectionChanged(i,i);
                grisViewCustomeAdapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }
}
