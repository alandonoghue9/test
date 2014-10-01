package com.CoraSystems.mobile.test.Timesheet;

import android.app.FragmentManager;

import java.util.ArrayList;

public class SectionsPagerAdapter extends android.support.v13.app.FragmentStatePagerAdapter {

    private ArrayList<timesheetDays> fragments;

    public SectionsPagerAdapter(FragmentManager fm, ArrayList<timesheetDays> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public timesheetDays getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 weeks (i set to 21 days in getFragments function in Timesheet activity)
        return fragments.size();
    }
}
