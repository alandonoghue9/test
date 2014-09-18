package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.CoraSystems.mobile.test.Graph.PieGraph;
import com.CoraSystems.mobile.test.Graph.PieSlice;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.TaskGlobal;
import com.CoraSystems.mobile.test.Objects.Task;

public class Stats extends Activity {

    int taskID;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stats);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        int complete = 60;

        Bundle bundle = getIntent().getExtras();
        taskID = bundle.getInt("task");

        for(int i=0;i< TaskGlobal.task.size();i++){
            if(taskID==TaskGlobal.task.get(i).getID()){
                task=TaskGlobal.task.get(i);
                i=TaskGlobal.task.size()-1;
            }
        }

        String taskDesc=task.getTask();
        String projectDesc=task.getProject();
        String start=task.getStart();
        String finish = task.getFinish();
        Double hoursComplete;
        Double thisWeek;
        Double percent=task.getCompletion()*100;

        TextView percentTextView = (TextView) this.findViewById(R.id.percent);
        percentTextView.setText(Double.toString(percent)+"%");
        TextView projectTextView = (TextView) this.findViewById(R.id.project_name);
        projectTextView.setText(projectDesc);
        TextView taskTextView = (TextView) this.findViewById(R.id.task_name);
        taskTextView.setText(taskDesc);
        TextView startTextView = (TextView) this.findViewById(R.id.start);
        startTextView.setText(start);
        TextView finishTextView = (TextView) this.findViewById(R.id.finish);
        finishTextView.setText(finish.substring(0,10));

        complete=(percent.intValue())*100;

        PieGraph pg = (PieGraph)this.findViewById(R.id.graph);
        PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor("#f26757"));
        slice.setValue(100-complete);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#1da9e1"));
        slice.setValue(complete);
        pg.addSlice(slice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.tasks:
                tasks();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void tasks(){
        Intent tasks = new Intent(this, MyActivity.class);
        startActivity(tasks);
    }
}