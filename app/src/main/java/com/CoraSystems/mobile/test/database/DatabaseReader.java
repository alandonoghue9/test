package com.CoraSystems.mobile.test.database;
        import java.util.ArrayList;
        import java.util.List;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import com.CoraSystems.mobile.test.database.DatabaseConstants.TaskConstants;
        import com.CoraSystems.mobile.test.Task;
        import com.CoraSystems.mobile.test.Services.JSONparser;
        import android.util.Log;

/**
 * Created by Alan on 8/5/2014.
 */
public class DatabaseReader {
    private SQLiteDatabase database;
    private OpenDbHelper  dbHelper;
    private static final String TAG = "Drawer ";

    private String[] allColumns = {
            TaskConstants.KEY_ID,
            TaskConstants.PROJECT,
            TaskConstants.PROJECTID,
            TaskConstants.TASK,
            TaskConstants.TASKID,
            TaskConstants.START_DATE,
            TaskConstants.FINISH_DATE,
            TaskConstants.PLANNED,
            TaskConstants.COMPLETE};

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
        int i;
        JSONparser jsoNparser = new JSONparser(data, context);
        if (jsoNparser.taskIdArraylist.size() > 0 ){
        for(i = 0; i < jsoNparser.taskIdArraylist.size(); i++);
        {
            ContentValues values = new ContentValues();
            //String[] whereArgs = {task.getTask()};
            //String where = TaskConstants.TASK + " = ?";
            values.put(TaskConstants.PROJECTID, jsoNparser.projectIdArrayList.get(i));
            values.put(TaskConstants.PROJECT, jsoNparser.projectDescArrayList.get(i));
            values.put(TaskConstants.TASKID, jsoNparser.taskIdArraylist.get(i));
            values.put(TaskConstants.TASK, jsoNparser.taskDescArrayList.get(i));
            values.put(TaskConstants.PLANNED, jsoNparser.plannedHoursArrayList.get(i));
            values.put(TaskConstants.START_DATE, jsoNparser.startDateArrayList.get(i));
            values.put(TaskConstants.FINISH_DATE, jsoNparser.finishDateArrayList.get(i));
            values.put(TaskConstants.COMPLETE, jsoNparser.completeArrayList.get(i));
            //int numberRowsUpdated;
/*            numberRowsUpdated = database.update(
                    TaskConstants.DATABASE_TABLE,
                    values, where, whereArgs);*/
            //if (numberRowsUpdated == 0) {
                long taskId = database.insert(TaskConstants.DATABASE_TABLE, null, values);

                Cursor cursor = database.query(TaskConstants.DATABASE_TABLE,
                        allColumns, TaskConstants.KEY_ID + " = "
                                + taskId, null, null, null, null
                );

                cursor.moveToFirst();

                //Task newTask = cursorToTask(cursor);
                cursor.close();

           // }
        }}
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

    public Task getTask(int id)
    {
        Cursor cursor = database.query(TaskConstants.DATABASE_TABLE,
                allColumns, TaskConstants.KEY_ID+" = ?", new String[] {String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();

        if (cursor !=  null)
            cursor.moveToFirst();
        Task task = new Task(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                cursor.getString(8));
        return task;
    }
}
