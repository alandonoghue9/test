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

    private static final String TASK_DATABASE_CREATE = "create table "+
            TaskConstants.TASK_DATABASE_TABLE + "("+
            TaskConstants.TASK_KEY_ID +
            " integer primary key autoincrement, " +
            TaskConstants.TASK + " text not null, " +
            TaskConstants.PROJECT + " text not null, " +
            TaskConstants.START_DATE + " text not null, " +
            TaskConstants.FINISH_DATE + " text not null, " +
            TaskConstants.PLANNED + " text not null, " +
            TaskConstants.COMPLETE + " real not null, " +
            TaskConstants.PROJECTID + " integer not null, " +
            TaskConstants.TASKID + " integer not null);";

    private static final String BYDAY_DATABASE_CREATE = "create table "+
            TaskConstants.BYDAY_DATABASE_TABLE + "("+
            TaskConstants.BYDAY_KEY_ID +
            " integer primary key autoincrement, " +
            TaskConstants.COMMENT + " text not null, " +
            TaskConstants.PLANNEDAY + " text not null, " +
            TaskConstants.DATE + " text not null, " +
            TaskConstants.COMPLETE + " real not null, " +
            TaskConstants.TIMESTAMP + " text not null, " +
            TaskConstants.BYDAY_TASKID + " integer not null);";

    private static final String CONFIG_DATABASE_CREATE = "create table "+
            TaskConstants.CONFIG_DATABASE_TABLE + "("+
            TaskConstants.CONFIG_KEY_ID +
            " integer primary key autoincrement, " +
            TaskConstants.MAXHOURS + " real not null, " +
            TaskConstants.MINHOURS + " real not null, " +
            TaskConstants.MAXMON + " real not null, " +
            TaskConstants.MINMON + " real not null, " +
            TaskConstants.MAXTUE + " real not null, " +
            TaskConstants.MINTUE + " real not null, " +
            TaskConstants.MAXWED + " real not null, " +
            TaskConstants.MINWED + " real not null, " +
            TaskConstants.MAXTHUR + " real not null, " +
            TaskConstants.MINTHUR + " real not null, " +
            TaskConstants.MAXFRI + " real not null, " +
            TaskConstants.MINFRI + " real not null, " +
            TaskConstants.MAXSAT + " real not null, " +
            TaskConstants.MINSAT + " real not null, " +
            TaskConstants.MAXSUN + " real not null, " +
            TaskConstants.MINSUN + " real not null, " +
            TaskConstants.submission + " text not null);";

    private static final String TIMESHEET_DATABASE_CREATE = "create table "+
            TaskConstants.TIMESHEET_DATABASE_TABLE + "("+
            TaskConstants.TIMESHEET_KEY_ID +
            " integer primary key autoincrement, " +
            TaskConstants.PDFURL + " text not null, " +
            TaskConstants.PLANNEDHOURS + " real not null, " +
            TaskConstants.NOOFTASKS + " integer not null, " +
            TaskConstants.TOTALHOURS + " real not null, " +
            TaskConstants.MONHOURS + " real not null, " +
            TaskConstants.TUEHOURS + " real not null, " +
            TaskConstants.WEDHOURS + " real not null, " +
            TaskConstants.THURHOURS + " real not null, " +
            TaskConstants.FRIHOURS + " real not null, " +
            TaskConstants.SATHOURS + " real not null, " +
            TaskConstants.SUNHOURS + " real not null, " +
            TaskConstants.SUBMITTED + " integer not null);";

  /*  private static final String LOCAL_DATABASE_CREATE = "create table "+
            TaskConstants.LOCAL_DATABASE_TABLE + "("+
            TaskConstants.LOCAL_KEY_ID +
            " integer primary key autoincrement, " +
            TaskConstants.COMMENTLOCAL + " text not null, " +
            TaskConstants.DATELOCAL + " text not null, " +
            TaskConstants.COMPLETELOCAL + " real not null, " +
            TaskConstants.TIMESTAMPLOCAL + " text not null, " +
            TaskConstants.TASKIDLOCAL + " integer not null);";*/


    public OpenDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TASK_DATABASE_CREATE);
        sqLiteDatabase.execSQL(TIMESHEET_DATABASE_CREATE);
        sqLiteDatabase.execSQL(CONFIG_DATABASE_CREATE);
        sqLiteDatabase.execSQL(BYDAY_DATABASE_CREATE);
        //sqLiteDatabase.execSQL(LOCAL_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TaskConstants.TASK_DATABASE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TaskConstants.CONFIG_DATABASE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TaskConstants.TIMESHEET_DATABASE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TaskConstants.BYDAY_DATABASE_TABLE);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TaskConstants.LOCAL_DATABASE_TABLE);

        onCreate(sqLiteDatabase);
    }
}
