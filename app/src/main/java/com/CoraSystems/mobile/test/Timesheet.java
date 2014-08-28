package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.CoraSystems.mobile.test.Objects.Task;
import com.CoraSystems.mobile.test.Services.JSONparser;
import com.CoraSystems.mobile.test.database.DatabaseReader;

import java.util.ArrayList;

public class Timesheet extends Activity {

    public String ok;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    ArrayList<Task> task = new ArrayList<Task>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timesheet_main);

        setTitle("Timesheet");
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Fragment headerFragment = new TimesheetHeader();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.timesheet_frag_header, headerFragment, "Frag_Main_tag");
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timesheet, menu);
        //Button button = (Button) findViewById(R.id.button);

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchService fetchservice = new fetchService();
                fetchservice.execute();
            }
        });*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class fetchService extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String dataService="";
            try {
                //SoapWebService soapWebService = new SoapWebService("alan", "password", uploadAndDownload.this);
                //dataService = soapWebService.SendThisData("hello", 200000);
                DatabaseReader databaseReader = new DatabaseReader();
                databaseReader.DataSource(Timesheet.this);
                databaseReader.reOpen();
                task = databaseReader.getProjectsTasks();



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
