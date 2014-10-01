package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.CoraSystems.mobile.test.Graph.PieGraph;
import com.CoraSystems.mobile.test.Graph.PieSlice;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.ByDayGlobal;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.TaskGlobal;
import com.CoraSystems.mobile.test.Objects.Task;
import com.CoraSystems.mobile.test.TaskList.TaskList;

public class Stats extends Activity {

    int taskID;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stats);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        int complete = 60;

        LinearLayout comp;
        LinearLayout plan;

        Bundle bundle = getIntent().getExtras();
        taskID = bundle.getInt("task");

        for(int i=0;i< TaskGlobal.task.size();i++){
            if(taskID==TaskGlobal.task.get(i).getTaskId()){
                task=TaskGlobal.task.get(i);
                i=TaskGlobal.task.size()-1;
            }
        }

        Double hoursComplete=0.0;
        //this week requires an iteration through dates of the week
        Double thisWeek=0.0;

        for(int i=0;i< ByDayGlobal.ByDayConstantsList.size();i++){
            if(taskID==ByDayGlobal.ByDayConstantsList.get(i).gettaskId()){
                hoursComplete = hoursComplete + ByDayGlobal.ByDayConstantsList.get(i).getHours();
            }
        }

        double hrsComplete=hoursComplete;

        comp = (LinearLayout)this.findViewById(R.id.complete);
        plan = (LinearLayout)this.findViewById(R.id.planned);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams c = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        thisWeek = (thisWeek/hrsComplete)*100;
        hrsComplete=100-thisWeek;

        //if no hours clocked bar displays red
        if(hoursComplete < 1){
            comp.setBackgroundColor(this.getResources().getColor(R.color.cora_red));
            c.weight = 100;
            p.weight = 0;
        }
        else {
            c.weight = (float) (100-hrsComplete);
            p.weight = (float) hrsComplete;
        }
        comp.setLayoutParams(c);
        plan.setLayoutParams(p);

        String taskDesc=task.getTask();
        String projectDesc=task.getProject();
        String start=task.getStart();
        String finish = task.getFinish();
        Double percent=task.getCompletion();
        complete=(percent.intValue())*100;
        String hoursComp = Integer.toString(hoursComplete.intValue());
        String currentWeek = Integer.toString(thisWeek.intValue());

        TextView percentTextView = (TextView) this.findViewById(R.id.percent);
        percentTextView.setText((Integer.toString(complete))+"%");

        TextView projectTextView = (TextView) this.findViewById(R.id.project_name);
        projectTextView.setText(projectDesc);

        TextView taskTextView = (TextView) this.findViewById(R.id.task_name);
        taskTextView.setText(taskDesc);

        TextView startTextView = (TextView) this.findViewById(R.id.start);
        startTextView.setText(start.substring(0,10));

        TextView finishTextView = (TextView) this.findViewById(R.id.finish);
        finishTextView.setText(finish.substring(0,10));

        TextView hoursCompelete = (TextView) this.findViewById(R.id.hours_complete);
        hoursCompelete.setText(hoursComp);

        /* Hours worked this week */
        TextView hoursWeek = (TextView) this.findViewById(R.id.hours_week);
        hoursWeek.setText(currentWeek);

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
        Intent tasks = new Intent(this, TaskList.class);
        startActivity(tasks);
    }
}