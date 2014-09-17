package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.CoraSystems.mobile.test.Objects.ObjectConstants.taskGlobal;
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

        double complete;
        double planned;

        comp = (LinearLayout)view.findViewById(R.id.complete);
        plan = (LinearLayout)view.findViewById(R.id.planned);

        taskTextView = (TextView)view.findViewById(R.id.teask_timesheet);
        projectTextView = (TextView)view.findViewById(R.id.project_timesheet);
        percentTextView = (TextView)view.findViewById(R.id.percent);

        LinearLayout.LayoutParams c = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //complete = ((int)(Math.random()*(100)));
        //planned = 100-complete;

        int count = bundle.getInt("count");
        complete = taskGlobal.task.get(count).getCompletion();
        planned = taskGlobal.task.get(count).getCompletion();
        projectTextView.setText(taskGlobal.task.get(count).getProject()+" ("+(taskGlobal.task.get(count).getProjectId())+")");
        taskTextView.setText(taskGlobal.task.get(count).getTask()+" ("+(taskGlobal.task.get(count).getTaskId())+")");
        percentComp = (Double.toString(100*complete)+"%");
        percentTextView.setText(percentComp);
        //c.weight = (float)complete;
        //p.weight = (float)(100-complete);

        complete = complete +0.3; //testing
        if(complete<0.30) {
            p.weight = (float)(100*complete);
            comp.setBackgroundColor(getActivity().getResources().getColor(R.color.cora_red));
            c.weight = (float)(100 - 100*complete);
        }
        else if(complete<0.70) {
            p.weight = (float)(100*complete);
            comp.setBackgroundColor(getActivity().getResources().getColor(R.color.cora_blue));
            c.weight = (float)(100 - 100*complete);
        }
        else if(complete>0.70){
            comp.setBackgroundColor(getActivity().getResources().getColor(R.color.cora_green));
            p.weight = 100;
            c.weight = 0;
        }

        comp.setLayoutParams(c);
        plan.setLayoutParams(p);

        return view;
    }
}
