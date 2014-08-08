package com.CoraSystems.mobile.test.database;

/**
 * Created by Alan on 7/29/2014.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.CoraSystems.mobile.test.database.DatabaseConstants.TaskConstants;
public class OpenDbHelper extends SQLiteOpenHelper {

    private  static final String DB_NAME = "task_database_db.db";
    public static final int DB_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "+
            TaskConstants.DATABASE_TABLE + "("+
            TaskConstants.KEY_ID +
            " integer primary key autoincrement, " +
            TaskConstants.TASK + " text not null, " +
            TaskConstants.PROJECT + " text not null, " +
            TaskConstants.START_DATE + " text not null, " +
            TaskConstants.FINISH_DATE + " text not null, " +
            TaskConstants.PLANNED + " text not null, " +
            TaskConstants.COMPLETE + " real not null, " +
            TaskConstants.PROJECTID + " integer not null, " +
            TaskConstants.TASKID + " integer not null);";

    public OpenDbHelper(Context context) {
        super(context, TaskConstants.DATABASE_TABLE, null, TaskConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "
                + TaskConstants.DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
