package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
        // Show 3 weeks
        return fragments.size();
    }
}
