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
    View.OnClickListener clickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timesheet_header, container, false);
        Bundle bundle = this.getArguments();
        double complete;
        double planned;
        comp = (LinearLayout)view.findViewById(R.id.complete);
        plan = (LinearLayout)view.findViewById(R.id.planned);
        projectTextView = (TextView)view.findViewById(R.id.teask_timesheet);
        taskTextView = (TextView)view.findViewById(R.id.project_timesheet);

        //fetchService fetchservice = new fetchService();

        //
        // fetchservice.execute();

        LinearLayout.LayoutParams c = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //complete = ((int)(Math.random()*(100)));
        //planned = 100-complete;
        //TODO: fix parsing
        //complete = Double.valueOf(bundle.getString("complete"));
        //planned = Double.valueOf(bundle.getString("complete"));
        projectTextView.setText(bundle.getString("project"));
        taskTextView.setText(bundle.getString("task"));

        //c.weight = (float)complete;
        //p.weight = (float)planned;
        c.height = 30;
        p.height = 30;
        comp.setLayoutParams(c);
        plan.setLayoutParams(p);

        return view;
    }

    public class fetchService extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String dataService="";
            try {
                //SoapWebService soapWebService = new SoapWebService("alan", "password", uploadAndDownload.this);
                //dataService = soapWebService.SendThisData("hello", 200000);

                JSONparser jsoNparser = new JSONparser(dataService, getActivity());
                jsoNparser.parsedData();
                JSONparser jsoN1parser = new JSONparser(dataService, getActivity());
                jsoN1parser.parsedData();

                return null;
            }
            catch(Exception e){}
             /*catch (IOException ) {
                //e.printStackTrace();
            }*/

            return null;
        }


    }
}
