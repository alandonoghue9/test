package com.CoraSystems.mobile.test.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.CoraSystems.mobile.test.Objects.ByDay;
import com.CoraSystems.mobile.test.Objects.Config;
import com.CoraSystems.mobile.test.Objects.TimeSheet;
import com.CoraSystems.mobile.test.database.DatabaseConstants.TaskConstants;
import com.CoraSystems.mobile.test.Objects.Task;


import java.util.ArrayList;

/**
 * Created by Alan on 8/5/2014.
 */
public class DatabaseReader {

    public ArrayList<String> projectIdArrayList = new ArrayList<String>();
    public ArrayList<String> projectDescArrayList = new ArrayList<String>();
    public ArrayList<String> taskIdArraylist = new ArrayList<String>();
    public ArrayList<String> taskDescArrayList = new ArrayList<String>();
    public ArrayList<String> plannedHoursArrayList = new ArrayList<String>();
    public ArrayList<String> startDateArrayList = new ArrayList<String>();
    public ArrayList<String> finishDateArrayList = new ArrayList<String>();
    public ArrayList<String> completeArrayList = new ArrayList<String>();

    public double MAXHOURS;
    public double MINHOURS;
    public double MAXMON;
    public double MINMON;
    public double MAXTUE;
    public double MINTUE;
    public double MAXWED;
    public double MINWED;
    public double MAXTHUR;
    public double MINTHUR;
    public double MAXFRI;
    public double MINFRI;
    public double MAXSAT;
    public double MINSAT;
    public double MAXSUN;
    public double MINSUN;
    public String submission;

    public double plannedHours;
    public int noOfTasks;
    public double totalHours;
    public String pdfUrl;
    public boolean submitted;
    public double mondayHours;
    public double tuesdayHours;
    public double wednesdayHours;
    public double thursdayHours;
    public double fridayHours;
    public double saturdayHours;
    public double sundayHours;

    public ArrayList<String> commentArraylist = new ArrayList<String>();
    public ArrayList<String> plannedHoursByDayArrayList = new ArrayList<String>();
    public ArrayList<String> hoursArrayList = new ArrayList<String>();
    public ArrayList<String> timestampArrayList = new ArrayList<String>();
    public ArrayList<String> dateArrayList = new ArrayList<String>();
    public ArrayList<String> taskIdArraylist1 = new ArrayList<String>();

    private SQLiteDatabase database;
    private OpenDbHelper  dbHelper;
    private static final String TAG = "Drawer ";

    private String[] allColumns = {
            TaskConstants.TASK_KEY_ID,
            TaskConstants.PROJECT,
            TaskConstants.PROJECTID,
            TaskConstants.TASK,
            TaskConstants.TASKID,
            TaskConstants.START_DATE,
            TaskConstants.FINISH_DATE,
            TaskConstants.PLANNED,
            TaskConstants.COMPLETE};

    private String[] allColumnsConfig = {
            TaskConstants.MAXHOURS,
            TaskConstants.MINHOURS,
            TaskConstants.MAXMON,
            TaskConstants.MINMON,
            TaskConstants.MAXTUE,
            TaskConstants.MINTUE,
            TaskConstants.MAXWED,
            TaskConstants.MINWED,
            TaskConstants.MAXTHUR,
            TaskConstants.MINTHUR,
            TaskConstants.MAXFRI,
            TaskConstants.MINFRI,
            TaskConstants.MAXSAT,
            TaskConstants.MINSAT,
            TaskConstants.MAXSUN,
            TaskConstants.MINSUN,
            TaskConstants.submission};

    private String[] allColumnsTimesheet = {
            TaskConstants.PLANNEDHOURS,
            TaskConstants.NOOFTASKS,
            TaskConstants.TOTALHOURS,
            TaskConstants.PDFURL,
            TaskConstants.SUBMITTED,
            TaskConstants.MONHOURS,
            TaskConstants.TUEHOURS,
            TaskConstants.WEDHOURS,
            TaskConstants.THURHOURS,
            TaskConstants.FRIHOURS,
            TaskConstants.SATHOURS,
            TaskConstants.SUNHOURS};

    private String[] allColumnsByDay = {
            TaskConstants.COMMENT,
            TaskConstants.BYDAY_TASKID,
            TaskConstants.PLANNEDAY,
            TaskConstants.TIMESTAMP,
            TaskConstants.DATE};

    public void DataSource(Context context) {
        dbHelper = new OpenDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void reOpen() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addTask(String data, Context context) {
        int counter=0;
        if (taskIdArraylist.size() > 0 ){
            while(counter < taskIdArraylist.size()){
            ContentValues values = new ContentValues();
            //String[] whereArgs = {task.getTask()};
            //String where = TaskConstants.TASK + " = ?";
            values.put(TaskConstants.PROJECTID, projectIdArrayList.get(counter));
            values.put(TaskConstants.PROJECT, projectDescArrayList.get(counter));
            values.put(TaskConstants.TASKID, taskIdArraylist.get(counter));
            values.put(TaskConstants.TASK, taskDescArrayList.get(counter));
            values.put(TaskConstants.PLANNED, plannedHoursArrayList.get(counter));
            values.put(TaskConstants.START_DATE, taskIdArraylist.get(counter));
            values.put(TaskConstants.FINISH_DATE, finishDateArrayList.get(counter));
            values.put(TaskConstants.COMPLETE, completeArrayList.get(counter));
            int numberRowsUpdated;
/*            numberRowsUpdated = database.update(
                    TaskConstants.DATABASE_TABLE,
                    values, where, whereArgs);*/
  //          if (numberRowsUpdated == 0) {
                long taskId = database.insert(TaskConstants.TASK_DATABASE_TABLE, null, values);

                Cursor cursor = database.query(TaskConstants.TASK_DATABASE_TABLE,
                        allColumns, TaskConstants.TASK_KEY_ID + " = "
                                + taskId, null, null, null, null
                );
                cursor.moveToFirst();

                //Task newTask = cursorToTask(cursor);
                cursor.close();
                counter++;
           // }
        }}
    }
    public void addConfig(String data, Context context) {
        //if (MAXHOURS > 0){
   ContentValues values = new ContentValues();
                //String[] whereArgs = {task.getTask()};
                //String where = TaskConstants.TASK + " = ?";
                values.put(TaskConstants.MAXHOURS, MAXHOURS);
                values.put(TaskConstants.MINHOURS, MINHOURS);
                values.put(TaskConstants.MAXMON, MAXMON);
                values.put(TaskConstants.MINMON, MINMON);
                values.put(TaskConstants.MAXTUE, MAXTUE);
                values.put(TaskConstants.MINTUE, MINTUE);
                values.put(TaskConstants.MAXWED, MAXWED);
                values.put(TaskConstants.MINWED, MINWED);
                values.put(TaskConstants.MAXTHUR, MAXTHUR);
                values.put(TaskConstants.MINTHUR, MINTHUR);
                values.put(TaskConstants.MAXFRI, MAXFRI);
                values.put(TaskConstants.MINFRI, MINFRI);
                values.put(TaskConstants.MAXSAT, MAXSAT);
                values.put(TaskConstants.MINSAT, MINSAT);
                values.put(TaskConstants.MAXSUN, MAXSUN);
                values.put(TaskConstants.MINSUN, MINSUN);
                values.put(TaskConstants.submission, submission);
                int numberRowsUpdated;
          // numberRowsUpdated = database.update(
      /*              TaskConstants.DATABASE_TABLE,
                    values, where, whereArgs);*/
                //          if (numberRowsUpdated == 0) {
                long configId = database.insert(TaskConstants.CONFIG_DATABASE_TABLE, null, values);


                Cursor cursor = database.query(TaskConstants.CONFIG_DATABASE_TABLE,
                        allColumnsConfig, TaskConstants.CONFIG_KEY_ID + " = "
                                + configId, null, null, null, null
                );
                cursor.moveToFirst();

                //Task newTask = cursorToTask(cursor);
                cursor.close();

                // }
            //}
    }
    public void addTimesheet(String data, Context context) {
        ContentValues values = new ContentValues();
        //String[] whereArgs = {task.getTask()};
        //String where = TaskConstants.TASK + " = ?";
        values.put(TaskConstants.MAXHOURS, MAXHOURS);
        values.put(TaskConstants.MINHOURS, MINHOURS);
        values.put(TaskConstants.MAXMON, MAXMON);
        values.put(TaskConstants.MINMON, MINMON);
        values.put(TaskConstants.MAXTUE, MAXTUE);
        values.put(TaskConstants.MINTUE, MINTUE);
        values.put(TaskConstants.MAXWED, MAXWED);
        values.put(TaskConstants.MINWED, MINWED);
        values.put(TaskConstants.MAXTHUR, MAXTHUR);
        values.put(TaskConstants.MINTHUR, MINTHUR);
        values.put(TaskConstants.MAXFRI, MAXFRI);
        values.put(TaskConstants.MINFRI, MINFRI);
        values.put(TaskConstants.MAXSAT, MAXSAT);
        values.put(TaskConstants.MINSAT, MINSAT);
        values.put(TaskConstants.MAXSUN, MAXSUN);
        values.put(TaskConstants.MINSUN, MINSUN);
        values.put(TaskConstants.submission, submission);
        int numberRowsUpdated;
        // numberRowsUpdated = database.update(
      /*              TaskConstants.DATABASE_TABLE,
                    values, where, whereArgs);*/
        //          if (numberRowsUpdated == 0) {
        long configId = database.insert(TaskConstants.CONFIG_DATABASE_TABLE, null, values);


        Cursor cursor = database.query(TaskConstants.CONFIG_DATABASE_TABLE,
                allColumnsConfig, TaskConstants.CONFIG_KEY_ID + " = "
                        + configId, null, null, null, null
        );
        cursor.moveToFirst();

        //Task newTask = cursorToTask(cursor);
        cursor.close();

        // }
        //}
    }
    public void addByDay(String data, Context context) {
        int counter=0;
        if (taskIdArraylist.size() > 0 ){
            while(counter < timestampArrayList.size()){
                ContentValues values = new ContentValues();
                //String[] whereArgs = {task.getTask()};
                //String where = TaskConstants.TASK + " = ?";
                values.put(TaskConstants.COMMENT, commentArraylist.get(counter));
                values.put(TaskConstants.PLANNEDHOURS,Double.parseDouble(plannedHoursByDayArrayList.get(counter)));
                values.put(TaskConstants.TOTALHOURS, Double.parseDouble(hoursArrayList.get(counter)));
                values.put(TaskConstants.TIMESTAMP, timestampArrayList.get(counter));
                values.put(TaskConstants.DATE, dateArrayList.get(counter));
                values.put(TaskConstants.BYDAY_TASKID, taskIdArraylist1.get(counter));

                int numberRowsUpdated;
/*            numberRowsUpdated = database.update(
                    TaskConstants.DATABASE_TABLE,
                    values, where, whereArgs);*/
                //          if (numberRowsUpdated == 0) {
                long taskId = database.insert(TaskConstants.TASK_DATABASE_TABLE, null, values);

                Cursor cursor = database.query(TaskConstants.TASK_DATABASE_TABLE,
                        allColumns, TaskConstants.TASK_KEY_ID + " = "
                                + taskId, null, null, null, null
                );
                cursor.moveToFirst();

                //Task newTask = cursorToTask(cursor);
                cursor.close();
                counter++;
                // }
            }}
    }
    public ArrayList<Task> getProjectsTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Cursor cursor = database.query(
                DatabaseConstants.TaskConstants.TASK_DATABASE_TABLE, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Task task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return tasks;
    }
    public Config getConfig() {
        Config config = null;
        Cursor cursor = database.query(
                TaskConstants.CONFIG_DATABASE_TABLE, allColumnsConfig, null, null, null, null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()){
             config = cursorToConfig(cursor);
        }
        cursor.close();
        database.close();
        return config;
    }
    public ArrayList<ByDay> getByDay() {
        ArrayList<ByDay> byDays = new ArrayList<ByDay>();
        Cursor cursor = database.query(
                TaskConstants.BYDAY_DATABASE_TABLE, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ByDay byDay = cursorToByDay(cursor);
            byDays.add(byDay);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return byDays;
    }

    public ArrayList<TimeSheet> getTimeSheet() {
        ArrayList<TimeSheet> timeSheets = new ArrayList<TimeSheet>();
        Cursor cursor = database.query(
                TaskConstants.TIMESHEET_DATABASE_TABLE, allColumnsTimesheet, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            TimeSheet timeSheet = cursorToTimesheet(cursor);
            timeSheets.add(timeSheet);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return timeSheets;
    }

   /* public String[] getDrawerTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        int projectCounter = 0,j=0;
        Task task;
        Cursor cursor = database.query(
                DatabaseConstants.MyConstants.DATABASE_TABLE, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            task = cursorToTask(cursor);
            task.setId(1);
            for(int i =0; i < tasks.size(); i++){
                if(task.getProject().equals(tasks.get(i).getProject())){

                    projectCounter++;
                }}

            if(projectCounter != 0){

            }
            else{tasks.add(task);
                projectCounter=0;}
            cursor.moveToNext();
        }

        String[] taskString = new String[tasks.size()*2];
        for (int i = 0; i < tasks.size()*2; i=i+2){
            taskString[i] =  tasks.get(j).getProject();
            taskString[i+1] = Integer.toString(tasks.get(j).getID());
            j++;
            Log.i(TAG, taskString[i] + " " + taskString[i+1]);}
        cursor.close();
        database.close();
        return taskString;
    }*/

    private Task cursorToTask(Cursor cursor) {
        Task task = new Task(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                cursor.getString(8));
        return task;

    }
    private TimeSheet cursorToTimesheet(Cursor cursor) {
        TimeSheet timesheet = new TimeSheet(cursor.getInt(0), cursor.getDouble(1), cursor.getInt(2), cursor.getDouble(3),
                cursor.getDouble(4), cursor.getDouble(5), cursor.getDouble(6),
                cursor.getDouble(7), cursor.getDouble(8), cursor.getDouble(9), cursor.getString(10),
                cursor.getInt(11));
        return timesheet;
    }
    private Config cursorToConfig(Cursor cursor) {
                Config config = new Config(cursor.getDouble(0), cursor.getDouble(1), cursor.getDouble(2),
                        cursor.getDouble(3), cursor.getDouble(4), cursor.getDouble(5),
                        cursor.getDouble(6), cursor.getDouble(7), cursor.getDouble(8),
                        cursor.getDouble(9), cursor.getDouble(10), cursor.getDouble(11),
                cursor.getDouble(12), cursor.getDouble(13), cursor.getDouble(14), cursor.getDouble(15),
                cursor.getString(16));
        return config;
    }
    private ByDay cursorToByDay(Cursor cursor) {
        ByDay byDay = new ByDay(cursor.getString(0), cursor.getDouble(1), cursor.getDouble(2), cursor.getString(3),
                cursor.getString(4), cursor.getInt(5));
        return byDay;
    }

    public Task getTask(int id)
    {
        Cursor cursor = database.query(TaskConstants.TASK_DATABASE_TABLE,
                allColumns, TaskConstants.TASK_KEY_ID+" = ?", new String[] {String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();

        if (cursor !=  null)
            cursor.moveToFirst();
        Task task = new Task(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                cursor.getString(8));
        return task;
    }
}
