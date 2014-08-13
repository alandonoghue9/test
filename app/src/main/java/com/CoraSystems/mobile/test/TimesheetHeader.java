package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by eoghanmartin on 12/08/2014.
 */
public class TimesheetHeader extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timesheet_header, container, false);
        return view;
    }
}
