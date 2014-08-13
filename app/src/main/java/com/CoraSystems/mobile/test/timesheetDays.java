package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class timesheetDays extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static timesheetDays newInstance(int sectionNumber) {
        timesheetDays fragment = new timesheetDays();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public timesheetDays() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GridView gridView;
        GridViewCustomAdapter grisViewCustomeAdapter;
        View rootView = inflater.inflate(R.layout.gridview_layout, container, false);
        //GridView gridview = (GridView) getView().findViewById(R.id.gridview);
        gridView=(GridView)rootView.findViewById(R.id.gridview);
        grisViewCustomeAdapter = new GridViewCustomAdapter(this.getActivity());
        gridView.setAdapter(grisViewCustomeAdapter);
        //gridview.setAdapter(new ImageAdapter(this.getActivity()));
        return rootView;
    }
}
