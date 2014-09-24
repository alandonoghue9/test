package com.CoraSystems.mobile.test;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class TimesheetHeader extends Fragment {

    LinearLayout comp;
    LinearLayout plan;
    TextView projectTextView;
    TextView taskTextView;
    TextView percentTextView;
    View.OnClickListener clickListener;
    static String percentComp;
    public double complete;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.timesheet_header, container, false);

        Bundle bundle = this.getArguments();
        double planned;

        comp = (LinearLayout) view.findViewById(R.id.complete);
        plan = (LinearLayout) view.findViewById(R.id.planned);

        taskTextView = (TextView) view.findViewById(R.id.teask_timesheet);
        projectTextView = (TextView) view.findViewById(R.id.project_timesheet);
        percentTextView = (TextView) view.findViewById(R.id.percent);

        LinearLayout.LayoutParams c = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //complete = ((int)(Math.random()*(100)));
        //planned = 100-complete;

        complete = bundle.getDouble("complete");
        planned = bundle.getDouble("planned");
        //projectTextView.setText(taskGlobal.task.get(count).getProject() + " (" + (taskGlobal.task.get(count).getProjectId()) + ")");
        projectTextView.setText(bundle.getString("project")+ " (" + bundle.getInt("projectID")+")");
        taskTextView.setText(bundle.getString("task desc"));
        percentComp = (Double.toString(100 * complete) + "%");
        percentTextView.setText(percentComp);
        //c.weight = (float)complete;
        //p.weight = (float)(100-complete);

        complete = complete + 0.3; //testing
        if (complete < 0.30) {
            p.weight = (float) (100 * complete);
            comp.setBackgroundColor(getActivity().getResources().getColor(R.color.cora_red));
            c.weight = (float) (100 - 100 * complete);
        } else if (complete < 0.70) {
            p.weight = (float) (100 * complete);
            comp.setBackgroundColor(getActivity().getResources().getColor(R.color.cora_blue));
            c.weight = (float) (100 - 100 * complete);
        } else if (complete > 0.70) {
            comp.setBackgroundColor(getActivity().getResources().getColor(R.color.cora_green));
            p.weight = 100;
            c.weight = 0;
        }

        comp.setLayoutParams(c);
        plan.setLayoutParams(p);

        ImageView edit = (ImageView) view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    public void show(){

        final Dialog d = new Dialog(getActivity());
        d.setTitle("Percentage Complete");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(100);
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("test",(String.valueOf(np.getValue())));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }
}
