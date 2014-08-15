package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by eoghanmartin on 12/08/2014.
 */
public class TimesheetHeader extends Fragment {

    LinearLayout comp;
    LinearLayout plan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timesheet_header, container, false);

        int complete;
        int planned;
        comp = (LinearLayout)view.findViewById(R.id.complete);
        plan = (LinearLayout)view.findViewById(R.id.planned);

        LinearLayout.LayoutParams c = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        complete = ((int)(Math.random()*(5)));
        planned = 5-complete;

        c.weight = complete;
        p.weight = planned;
        c.height = 30;
        p.height = 30;
        comp.setLayoutParams(c);
        plan.setLayoutParams(p);
        //comp.addView(comp);

        return view;
    }
}
