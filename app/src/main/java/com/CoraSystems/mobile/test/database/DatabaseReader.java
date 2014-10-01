package com.CoraSystems.mobile.test.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.CoraSystems.mobile.test.Objects.ByDay;
import com.CoraSystems.mobile.test.Objects.Config;
import com.CoraSystems.mobile.test.Objects.LocalSave;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.ByDayGlobal;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.ConfigConstants;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.LocalSaveGlobal;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.TaskGlobal;
import com.CoraSystems.mobile.test.Objects.TimeSheet;
import com.CoraSystems.mobile.test.Objects.TimeSheetStatus;
import com.CoraSystems.mobile.test.Objects.User;
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

    public ArrayList<String> plannedHoursTimeArrayList = new ArrayList<String>();
    public ArrayList<String> noOfTasksArrayList = new ArrayList<String>();
    public ArrayList<String> totalHoursArraylist = new ArrayList<String>();
    public ArrayList<String> pdfUrlArrayList = new ArrayList<String>();
    public ArrayList<String> submittedArrayList = new ArrayList<String>();
    public ArrayList<String> mondayHoursArrayList = new ArrayList<String>();
    public ArrayList<String> tuesdayHoursArrayList = new ArrayList<String>();
    public ArrayList<String> wednesdayHoursArrayList = new ArrayList<String>();
    public ArrayList<String> thursdayHoursArraylist = new ArrayList<String>();
    public ArrayList<String> fridayHoursArrayList = new ArrayList<String>();
    public ArrayList<String> saturdayHoursArrayList = new ArrayList<String>();
    public ArrayList<String> sundayHoursArrayList = new ArrayList<String>();

    public ArrayList<String> commentArraylist = new ArrayList<String>();
    public ArrayList<String> plannedHoursByDayArrayList = new ArrayList<String>();
    public ArrayList<String> hoursArrayList = new ArrayList<String>();
    public ArrayList<String> timestampArrayList = new ArrayList<String>();
    public ArrayList<String> dateArrayList = new ArrayList<String>();
    public ArrayList<String> taskIdArraylist1 = new ArrayList<String>();
    public ArrayList<String> actualIdArraylist = new ArrayList<String>();


    public ArrayList<String> commentLocalArraylist = new ArrayList<String>();
    public ArrayList<String> completLocalArrayList = new ArrayList<String>();
    public ArrayList<String> hoursByDayArrayList = new ArrayList<String>();
    public ArrayList<String> timestampLocalArrayList = new ArrayList<String>();
    public ArrayList<String> dateLocalArrayList = new ArrayList<String>();
    public ArrayList<String> taskIdLocalArraylist = new ArrayList<String>();
    public ArrayList<String> actualIdLocalArraylist = new ArrayList<String>();
    public ArrayList<String> hoursPlannedByDayArrayList = new ArrayList<String>();

    public ArrayList<String> timeSheetIdArraylist = new ArrayList<String>();
    public ArrayList<String> startTimeSheetArraylist = new ArrayList<String>();
    public ArrayList<String> finishTimeSheetArrayList = new ArrayList<String>();
    public ArrayList<String> statusArrayList = new ArrayList<String>();
    public ArrayList<String> statusIDArrayList = new ArrayList<String>();
    public ArrayList<String> statusDescriptionArraylist = new ArrayList<String>();


    private SQLiteDatabase database;
    private OpenDbHelper  dbHelper;
    private static final String TAG = "Drawer ";

    private String[] allColumns = {
            TaskConstants.TASK_KEY_ID,
            TaskConstants.PROJECTID,
            TaskConstants.PROJECT,
            TaskConstants.TASKID,
            TaskConstants.TASK,
            TaskConstants.START_DATE,
            TaskConstants.FINISH_DATE,
            TaskConstants.PLANNED,
            TaskConstants.COMPLETE};

    private String[] allColumnsConfig = {
            TaskConstants.CONFIG_KEY_ID,
            TaskConstants.MAXHOURS,
            TaskConstants.MINHOURS,
            TaskConstants.MAXMON,
            TaskConstants.MAXTUE,
            TaskConstants.MAXWED,
            TaskConstants.MAXTHUR,
            TaskConstants.MAXFRI,
            TaskConstants.MAXSAT,
            TaskConstants.MAXSUN};

    private String[] allColumnsTimesheet = {
            TaskConstants.TIMESHEET_KEY_ID,
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

    private String[] allColumnsUser = {
            TaskConstants.USER_KEY_ID,
            TaskConstants.USER,
            TaskConstants.PASSWORD,
            TaskConstants.BASEURL};

    private String[] allColumnsTimesheetStatus = {
            TaskConstants.TIMESHEET_STATUS_KEY_ID,
            TaskConstants.TIMESHEETID,
            TaskConstants.STARTTIMESTAT,
            TaskConstants.FINISHTIMESTAT,
            TaskConstants.STATUS,
            TaskConstants.STATUSID,
            TaskConstants.STATUSDESCRIPTION};

    private String[] allColumnsByDay = {
            TaskConstants.BYDAY_KEY_ID,
            TaskConstants.COMMENT,
            TaskConstants.PLANNEDAY,
            TaskConstants.BYDAY_HOURSDAY,
            TaskConstants.TIMESTAMP,
            TaskConstants.DATE,
            TaskConstants.BYDAY_TASKID,
            TaskConstants.ACTUALID};

    private String[] allColumnsLocalSave = {
            TaskConstants.LOCAL_DATABASE_TABLE,
            TaskConstants.COMPLETELOCAL,
            TaskConstants.COMMENTLOCAL,
            TaskConstants.HOURSBYDAYLOCAL,
            TaskConstants.TASKIDLOCAL,
            TaskConstants.TIMESTAMPLOCAL,
            TaskConstants.DATELOCAL,
            TaskConstants.ACTUALIDLOCAL};

    public void DataSource(Context context) {
        dbHelper = /*new OpenDbHelper(context);*/OpenDbHelper.getInstance(context);
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

    public void addTask(Context context) {
        int counter=0;
        Task task;
        if (taskIdArraylist.size() > 0 ){
            while(counter < taskIdArraylist.size()) {
                ContentValues values = new ContentValues();
                //String[] whereArgs = {task.getTask()};
                //String where = TaskConstants.TASK + " = ?";
                values.put(TaskConstants.PROJECTID, Integer.parseInt(projectIdArrayList.get(counter)));
                values.put(TaskConstants.PROJECT, projectDescArrayList.get(counter));
                values.put(TaskConstants.TASKID, Integer.parseInt(taskIdArraylist.get(counter)));
                values.put(TaskConstants.TASK, taskDescArrayList.get(counter));
                values.put(TaskConstants.PLANNED, Double.parseDouble(plannedHoursArrayList.get(counter)));
                values.put(TaskConstants.START_DATE, startDateArrayList.get(counter));
                values.put(TaskConstants.FINISH_DATE, finishDateArrayList.get(counter));
                values.put(TaskConstants.COMPLETE, Double.parseDouble(completeArrayList.get(counter)));
                //int numberRowsUpdated;
/*            numberRowsUpdated = database.update(
                    TaskConstants.DATABASE_TABLE,
                    values, where, whereArgs);*/
                //          if (numberRowsUpdated == 0) {
                /*long byDayId = database.insert(TaskConstants.BYDAY_DATABASE_TABLE, null, values);

                Cursor cursor = database.query(TaskConstants.BYDAY_DATABASE_TABLE,
                        allColumnsByDay, TaskConstants.BYDAY_KEY_ID + " = "
                                + byDayId, null, null, null, null
                );*/

                //long taskId=0;
                //database = dbHelper.getWritableDatabase();
                long taskId = database.insert(TaskConstants.TASK_DATABASE_TABLE, null, values);
                Cursor cursor = database.query(TaskConstants.TASK_DATABASE_TABLE,
                        allColumns, TaskConstants.TASK_KEY_ID + " = "
                                + taskId, null, null, null, null);
                //task = cursorToTask(cursor);
                //taskGlobal.task.add(task);
                cursor.moveToFirst();
                cursor.close();
                counter++;
            }
           // }
        }
        TaskGlobal.task = getProjectsTasks();
    }
    public void addConfig(Context context) {
        if (MAXHOURS > 0) {
            ContentValues values = new ContentValues();
            values.put(TaskConstants.MAXHOURS, MAXHOURS);
            values.put(TaskConstants.MINHOURS, MINHOURS);
            values.put(TaskConstants.MAXMON, MAXMON);
            values.put(TaskConstants.MAXTUE, MAXTUE);
            values.put(TaskConstants.MAXWED, MAXWED);
            values.put(TaskConstants.MAXTHUR, MAXTHUR);
            values.put(TaskConstants.MAXFRI, MAXFRI);
            values.put(TaskConstants.MAXSAT, MAXSAT);
            values.put(TaskConstants.MAXSUN, MAXSUN);

            if (ConfigConstants.config == null) {
                long configId = database.insert(TaskConstants.CONFIG_DATABASE_TABLE, null, values);
                Cursor cursor = database.query(TaskConstants.CONFIG_DATABASE_TABLE,
                        allColumnsConfig, TaskConstants.CONFIG_KEY_ID + " = "
                                + configId, null, null, null, null);
                cursor.moveToFirst();
                cursor.close();
            } else {
                database.update(TaskConstants.CONFIG_DATABASE_TABLE, values,
                        "_id=" + ConfigConstants.config.getConfigId(), null);
            }
            ConfigConstants.config = getConfig();
        }
    }
    public void addTimesheet(Context context) {
        ContentValues values = new ContentValues();
        //String[] whereArgs = {task.getTask()};
        //String where = TaskConstants.TASK + " = ?";
        values.put(TaskConstants.PLANNEDHOURS, plannedHours);
        values.put(TaskConstants.NOOFTASKS, noOfTasks);
        values.put(TaskConstants.MONHOURS, mondayHours);
        values.put(TaskConstants.TUEHOURS, tuesdayHours);
        values.put(TaskConstants.WEDHOURS, wednesdayHours);
        values.put(TaskConstants.THURHOURS, thursdayHours);
        values.put(TaskConstants.FRIHOURS, fridayHours);
        values.put(TaskConstants.SATHOURS, saturdayHours);
        values.put(TaskConstants.SUNHOURS, sundayHours);
        values.put(TaskConstants.PDFURL, pdfUrl);
        values.put(TaskConstants.SUBMITTED, submitted);
        //int numberRowsUpdated;
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
        cursor.close();

        // }
        //}
    }
    public void addTimesheetStatus(Context context) {
        for (int counter = 0; counter < timeSheetIdArraylist.size(); counter++) {
            ContentValues values = new ContentValues();
            //String[] whereArgs = {task.getTask()};
            //String where = TaskConstants.TASK + " = ?";
            values.put(TaskConstants.TIMESHEETID, timeSheetIdArraylist.get(counter));
            values.put(TaskConstants.STARTTIMESTAT, startTimeSheetArraylist.get(counter));
            values.put(TaskConstants.FINISHTIMESTAT, finishTimeSheetArrayList.get(counter));
            values.put(TaskConstants.STATUS, statusArrayList.get(counter));
            values.put(TaskConstants.STATUSID, statusIDArrayList.get(counter));
            values.put(TaskConstants.STATUSDESCRIPTION, statusDescriptionArraylist.get(counter));
            //int numberRowsUpdated;
            // numberRowsUpdated = database.update(
      /*              TaskConstants.DATABASE_TABLE,
                    values, where, whereArgs);*/
            //          if (numberRowsUpdated == 0) {
            long TimeId = database.insert(TaskConstants.TIMESHEETSTATUS_DATABASE_TABLE, null, values);
            Cursor cursor = database.query(TaskConstants.TIMESHEETSTATUS_DATABASE_TABLE,
                    allColumnsTimesheetStatus, TaskConstants.TIMESHEET_STATUS_KEY_ID + " = "
                            + TimeId, null, null, null, null
            );
            cursor.moveToFirst();
            cursor.close();

            // }
            //}
        }
    }
    public void addUser(Context context) {
        ContentValues values = new ContentValues();
        //String[] whereArgs = {task.getTask()};
        //String where = TaskConstants.TASK + " = ?";
        values.put(TaskConstants.USER, plannedHours);
        values.put(TaskConstants.PASSWORD, noOfTasks);
        values.put(TaskConstants.BASEURL, mondayHours);
        //int numberRowsUpdated;
        // numberRowsUpdated = database.update(
      /*              TaskConstants.DATABASE_TABLE,
                    values, where, whereArgs);*/
        //          if (numberRowsUpdated == 0) {
        long UserId = database.insert(TaskConstants.USER_DATABASE_TABLE, null, values);
        Cursor cursor = database.query(TaskConstants.USER_DATABASE_TABLE,
                allColumnsUser, TaskConstants.USER_KEY_ID + " = "
                        + UserId, null, null, null, null
        );
        cursor.moveToFirst();
        cursor.close();

        // }
        //}
    }
    public void addByDay(Context context) {
        int counter=0;
        if (taskIdArraylist1.size() > 0 ){
            while(counter < taskIdArraylist1.size()){
                ContentValues values = new ContentValues();
                //String[] whereArgs = {task.getTask()};
                //String where = TaskConstants.TASK + " = ?";
                values.put(TaskConstants.COMMENT, commentArraylist.get(counter));
                values.put(TaskConstants.PLANNEDAY,Double.parseDouble(plannedHoursByDayArrayList.get(counter)));
                values.put(TaskConstants.TIMESTAMP, timestampArrayList.get(counter));
                values.put(TaskConstants.BYDAY_HOURSDAY,Double.parseDouble(hoursArrayList.get(counter)));
                values.put(TaskConstants.DATE, dateArrayList.get(counter));
                values.put(TaskConstants.BYDAY_TASKID, taskIdArraylist1.get(counter));
                values.put(TaskConstants.ACTUALID, Integer.parseInt(actualIdArraylist.get(counter)));

                //int numberRowsUpdated;
/*            numberRowsUpdated = database.update(
                    TaskConstants.DATABASE_TABLE,
                    values, where, whereArgs);*/
                //        if (numberRowsUpdated == 0) {
                long byDayId = database.insert(TaskConstants.BYDAY_DATABASE_TABLE, null, values);

                Cursor cursor = database.query(TaskConstants.BYDAY_DATABASE_TABLE,
                        allColumnsByDay, TaskConstants.BYDAY_KEY_ID + " = "
                                + byDayId, null, null, null, null
                );
                cursor.moveToFirst();

                //Task newTask = cursorToTask(cursor);
                cursor.close();
                counter++;
                // }
            }
        }
        ByDayGlobal.ByDayConstantsList=getByDay();
    }

    public void addLocalSave(Context context) {
        int counter = 0;
        if (taskIdLocalArraylist.size() > 0) {
            while (counter < taskIdLocalArraylist.size()) {
                ContentValues values = new ContentValues();
                //String[] whereArgs = {task.getTask()};
                //String where = TaskConstants.TASK + " = ?";
                values.put(TaskConstants.COMMENTLOCAL, commentLocalArraylist.get(counter));
                values.put(TaskConstants.COMPLETELOCAL, Double.parseDouble(completLocalArrayList.get(counter)));
                values.put(TaskConstants.HOURSBYDAYLOCAL, Double.parseDouble(hoursByDayArrayList.get(counter)));
                values.put(TaskConstants.TIMESTAMPLOCAL, timestampLocalArrayList.get(counter));
                values.put(TaskConstants.DATELOCAL, dateLocalArrayList.get(counter));
                values.put(TaskConstants.TASKIDLOCAL, Integer.parseInt(taskIdArraylist1.get(counter)));
                values.put(TaskConstants.ACTUALIDLOCAL, Integer.parseInt(actualIdLocalArraylist.get(counter)));
                //int numberRowsUpdated;
                /*numberRowsUpdated = database.update(
                    TaskConstants.DATABASE_TABLE,
                    values, where, whereArgs);*/
                //        if (numberRowsUpdated == 0) {
                long taskId = database.insert(TaskConstants.LOCAL_DATABASE_TABLE, null, values);

                Cursor cursor = database.query(TaskConstants.LOCAL_DATABASE_TABLE,
                        allColumnsLocalSave, TaskConstants.TASK_KEY_ID + " = "
                                + taskId, null, null, null, null);
                cursor.moveToFirst();
                cursor.close();
                counter++;
                // }
            }
            LocalSaveGlobal.LocalSaveArrayList = getLocalSave();
        }}
    public void updateLocalSave(Context context, LocalSave localSave) {
                ContentValues values = new ContentValues();

                values.put(TaskConstants.COMMENTLOCAL, localSave.getComment());
                values.put(TaskConstants.COMPLETELOCAL, localSave.getCompleteLocal());
                values.put(TaskConstants.HOURSBYDAYLOCAL, localSave.getHours());
                values.put(TaskConstants.TIMESTAMPLOCAL, localSave.getTimestamp());
                values.put(TaskConstants.DATELOCAL, localSave.getDate());
                values.put(TaskConstants.TASKIDLOCAL, localSave.gettaskId());

                database.update(TaskConstants.LOCAL_DATABASE_TABLE, values,
                        "_id=" + localSave.getLocalId(), null);


        LocalSaveGlobal.LocalSaveArrayList = getLocalSave();
    }



    public ArrayList<Task> getProjectsTasks() {
        /*ArrayList<ByDay> byDays = new ArrayList<ByDay>();
        Cursor cursor = database.query(
                TaskConstants.BYDAY_DATABASE_TABLE, allColumnsByDay, null, null, null, null, null);
        if(cursor==null){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ByDay byDay = cursorToByDay(cursor);
            byDays.add(byDay);
            cursor.moveToNext();
        }
        cursor.close();
        return byDays;*/
        ArrayList<Task> tasks = new ArrayList<Task>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(
                DatabaseConstants.TaskConstants.TASK_DATABASE_TABLE, allColumns, null, null, null, null, null);
        if(cursor==null){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Task task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();

        return tasks;
    }
    //
    // Get all of configuration Settings
    //
    public Config getConfig() {
        Config config = null;
        Cursor cursor = database.query(
                TaskConstants.CONFIG_DATABASE_TABLE, allColumnsConfig, null, null, null, null, null);
        if(cursor==null){
            return null;
        }
        cursor.moveToFirst();
        if (!cursor.isAfterLast()){
             config = cursorToConfig(cursor);
        }
        cursor.close();
        dbHelper.close();

        return config;
    }
    //
    // Get all of ByDay
    //
    public ArrayList<ByDay> getByDay() {
        ArrayList<ByDay> byDays = new ArrayList<>();
        ByDay byDay = null;
        Cursor cursor = database.query(
                TaskConstants.BYDAY_DATABASE_TABLE, allColumnsByDay, null, null, null, null, null);
        if(cursor==null){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            byDay = cursorToByDay(cursor);
            byDays.add(byDay);
            cursor.moveToNext();
        }
        cursor.close();

        dbHelper.close();

        return byDays;
    }
    //
    // Get ByDays with a given task Id
    //
    public ArrayList<ByDay> getByDayByTask(int TaskId) {
        ArrayList<ByDay> byDays = new ArrayList<ByDay>();
        Cursor cursor = database.query(
                TaskConstants.BYDAY_DATABASE_TABLE, allColumnsByDay, "bydaytaskid=?", new String[]{""+TaskId}, null, null, null);
        if(cursor==null){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ByDay byDay = cursorToByDay(cursor);
            byDays.add(byDay);
            cursor.moveToNext();
        }
        cursor.close();
        return byDays;
    }
    //
    // Get Local Database ByDay
    //
    public ArrayList<LocalSave> getLocalSave() {
        ArrayList<LocalSave> LocalSaves = new ArrayList<LocalSave>();
        Cursor cursor = database.query(
                TaskConstants.LOCAL_DATABASE_TABLE, allColumnsLocalSave, null, null, null, null, null);
        if(cursor==null){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            LocalSave localSave = cursorToLocalSave(cursor);
            LocalSaves.add(localSave);
            cursor.moveToNext();
        }
        cursor.close();
        return LocalSaves;
    }
    //
    // Get all the Time Sheet Statuses for the user
    //
    public ArrayList<TimeSheetStatus> getTimeSheetStatus() {
        ArrayList<TimeSheetStatus> TimeSheetStatuss = new ArrayList<TimeSheetStatus>();
        Cursor cursor = database.query(
                TaskConstants.TIMESHEETSTATUS_DATABASE_TABLE, allColumnsTimesheetStatus, null, null, null, null, null);
        if(cursor==null){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            TimeSheetStatus timeSheetStatuss = cursorToTimeSheetStatus(cursor);
            TimeSheetStatuss.add(timeSheetStatuss);
            cursor.moveToNext();
        }
        cursor.close();
        return TimeSheetStatuss;
    }
    public String getTimeSheetStatusMaxOrMinDate(String QueryDate, String maxOrMin) {
        String maxDate="";
        Cursor cursor = database.query(
                TaskConstants.TIMESHEETSTATUS_DATABASE_TABLE,  new String [] {maxOrMin+"("+QueryDate+")"}, null, null, null, null, null);
        if(cursor==null){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maxDate = cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();
        return maxDate;
    }
    public String getTimeSheetStatusMinDate() {
        String maxDate="";
        Cursor cursor = database.query(
                TaskConstants.TIMESHEETSTATUS_DATABASE_TABLE,  new String [] {"MIN("+TaskConstants.FINISHTIMESTAT+")"}, null, null, null, null, null);
        if(cursor==null){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maxDate = cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();
        return maxDate;
    }
    public ArrayList<User> getUser() {
        ArrayList<User> Users = new ArrayList<User>();
        Cursor cursor = database.query(
                TaskConstants.USER_DATABASE_TABLE, allColumnsUser, null, null, null, null, null);
        if(cursor==null){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = cursorToUser(cursor);
            Users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return Users;
    }
    public void deleteTask(ArrayList<Task> delTask, Context context){
        for (int i = 0; i < delTask.size(); i++){
            int id = delTask.get(i).getID();
            database.delete(TaskConstants.TASK_DATABASE_TABLE, TaskConstants.TASK_KEY_ID
                    + " = " + id, null);
        }
        TaskGlobal.delTask.clear();
    }
    public void deleteByDayTask(ArrayList<Task> delByDay, Context context){
        for (int i = 0; i < delByDay.size(); i++){
            int id = delByDay.get(i).getTaskId();
            database.delete(TaskConstants.BYDAY_DATABASE_TABLE, TaskConstants.BYDAY_TASKID
                    + " = " + id, null);
        }
    }
    public void deleteByDay(ArrayList<ByDay> delByDay, Context context){
        for (int i = 0; i < delByDay.size(); i++){
            int id = delByDay.get(i).getId();
            database.delete(TaskConstants.BYDAY_DATABASE_TABLE, TaskConstants.TASK_KEY_ID
                    + " = " + id, null);
        }
    }
    public void deleteTimeSheet(ArrayList<TimeSheet> delTimeSheet,  Context context){
        for (int i = 0; i < delTimeSheet.size(); i++){
            int id = delTimeSheet.get(i).getID();
            database.delete(TaskConstants.TIMESHEET_DATABASE_TABLE, TaskConstants.TIMESHEET_KEY_ID
                    + " = " + id, null);
        }
    }
    public void deleteLocalSave(ArrayList<LocalSave> delLocalsave, Context context){
        for (int i = 0; i < delLocalsave.size(); i++){
            int id = delLocalsave.get(i).getLocalId();
            database.delete(TaskConstants.LOCAL_DATABASE_TABLE, TaskConstants.LOCAL_KEY_ID
                    + " = " + id, null);
        }
    }

    public void deleteTimeSheetStatus(ArrayList<TimeSheetStatus> delTimeStats, Context context){
        for (int i = 0; i < delTimeStats.size(); i++){
            int id = delTimeStats.get(i).getID();
            database.delete(TaskConstants.TIMESHEETSTATUS_DATABASE_TABLE, TaskConstants.LOCAL_KEY_ID
                    + " = " + id, null);
        }
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

        return taskString;
    }*/

    private Task cursorToTask(Cursor cursor) {
        Task task = new Task(cursor.getInt(0),cursor.getInt(1), cursor.getString(2),
                cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                cursor.getDouble(7), cursor.getDouble(8));
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
                Config config = new Config(cursor.getInt(0),cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3),
                        cursor.getDouble(4), cursor.getDouble(5), cursor.getDouble(6),
                        cursor.getDouble(7), cursor.getDouble(8), cursor.getDouble(9));
        return config;
    }
    private ByDay cursorToByDay(Cursor cursor) {
        ByDay byDay = new ByDay(cursor.getInt(0),cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getString(4),
                cursor.getString(5), cursor.getInt(6), cursor.getInt(7));
        return byDay;
    }
    private TimeSheetStatus cursorToTimeSheetStatus(Cursor cursor) {
        TimeSheetStatus timeSheetStatus = new TimeSheetStatus(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getDouble(5), cursor.getDouble(6));
        return timeSheetStatus;
    }
    private User cursorToUser(Cursor cursor) {
        User user = new User(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return user;
    }
    private LocalSave cursorToLocalSave(Cursor cursor) {
        LocalSave localSave = new LocalSave(cursor.getInt(0), cursor.getString(1), cursor.getDouble(1), cursor.getDouble(2),cursor.getString(3), cursor.getString(4),
                cursor.getInt(5), cursor.getInt(6));
        return localSave;
    }

    /*private Task cursorToTask(Cursor cursor) {
        Task task = new Task(cursor.getInt(0),cursor.getInt(1), cursor.getString(2),
                cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                cursor.getString(7), cursor.getDouble(8));
        return task;*/
   /* public Task getTask(int id)
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
    }*/
}
