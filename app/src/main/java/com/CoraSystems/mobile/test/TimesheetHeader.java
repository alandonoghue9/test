package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.CoraSystems.mobile.test.database.DatabaseReader;

/**
 * Created by eoghanmartin on 12/08/2014.
 */
public class TimesheetHeader extends Fragment {

    LinearLayout comp;
    LinearLayout plan;
    Button button;
    View.OnClickListener clickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timesheet_header, container, false);

        int complete;
        int planned;
        comp = (LinearLayout)view.findViewById(R.id.complete);
        plan = (LinearLayout)view.findViewById(R.id.planned);

        fetchService fetchservice = new fetchService();

        fetchservice.execute();

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

        return view;
    }

    public class fetchService extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String dataService="";
            int check;

            try {
                //SoapWebService soapWebService = new SoapWebService("alan", "password", uploadAndDownload.this);
                //dataService = soapWebService.SendThisData("hello", 200000);

                //JSONparser jsoNparser = new JSONparser(dataService, uploadAndDownload.this);
                // check = jsoNparser.parsedData();
                DatabaseReader databaseReader = new DatabaseReader();
                databaseReader.DataSource(getActivity());
                databaseReader.open();
                databaseReader.addTask(dataService, getActivity());

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
