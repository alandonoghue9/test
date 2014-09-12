package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.CoraSystems.mobile.test.Services.JSONparser;
import com.CoraSystems.mobile.test.database.DatabaseReader;

public class TimesheetHeader extends Fragment {

    LinearLayout comp;
    LinearLayout plan;
    TextView projectTextView;
    TextView taskTextView;
    TextView percentTextView;
    View.OnClickListener clickListener;
    static String percentComp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timesheet_header, container, false);

        Bundle bundle = this.getArguments();

        int complete;
        int planned;

        comp = (LinearLayout)view.findViewById(R.id.complete);
        plan = (LinearLayout)view.findViewById(R.id.planned);

        taskTextView = (TextView)view.findViewById(R.id.teask_timesheet);
        projectTextView = (TextView)view.findViewById(R.id.project_timesheet);
        percentTextView = (TextView)view.findViewById(R.id.percent);

        LinearLayout.LayoutParams c = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //TODO: fix parsing
        projectTextView.setText(bundle.getString("project"));
        taskTextView.setText(bundle.getString("task desc"));
        //planned = bundle.getInt("planned");
        complete = bundle.getInt("complete");
        complete=complete+30;
        percentComp = (Integer.toString(complete)+"%");
        percentTextView.setText(percentComp);

        //c.weight = (float)complete;
        //p.weight = (float)(100-complete);

        if(complete<30) {
            p.weight = (float)complete;
            comp.setBackgroundColor(getActivity().getResources().getColor(R.color.cora_red));
            c.weight = (float)(100 - complete);
        }
        else if(complete<70) {
            p.weight = (float)complete;
            comp.setBackgroundColor(getActivity().getResources().getColor(R.color.cora_blue));
            c.weight = (float)(100 - complete);
        }
        else if(complete>70){
            comp.setBackgroundColor(getActivity().getResources().getColor(R.color.cora_green));
            p.weight = 100;
            c.weight = 0;
        }

        comp.setLayoutParams(c);
        plan.setLayoutParams(p);

        return view;
    }
}
