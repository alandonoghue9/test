package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.CoraSystems.mobile.test.R;
import com.CoraSystems.mobile.test.Services.JSONparser;
import com.CoraSystems.mobile.test.Services.SoapWebService;
import com.CoraSystems.mobile.test.database.DatabaseReader;

import java.io.IOException;

public class uploadAndDownload extends Activity {
public String ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timesheet_main);

        setTitle("Timesheet");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timesheet, menu);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchService fetchservice = new fetchService();
                fetchservice.execute();
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
            int check;

            try {
                //SoapWebService soapWebService = new SoapWebService("alan", "password", uploadAndDownload.this);
                //dataService = soapWebService.SendThisData("hello", 200000);

                //JSONparser jsoNparser = new JSONparser(dataService, uploadAndDownload.this);
               // check = jsoNparser.parsedData();
                DatabaseReader databaseReader = new DatabaseReader();
                databaseReader.DataSource(uploadAndDownload.this);
                databaseReader.open();
                databaseReader.addTask(dataService, uploadAndDownload.this);

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
