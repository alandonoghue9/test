package com.CoraSystems.mobile.test.database;

/**
 * Created by Alan on 7/29/2014.
 */
public class DatabaseConstants {

    public DatabaseConstants() {
        //empty constructor to prevent accidental instantiation
    }

    public static abstract class TaskConstants {
        public static final String DATABASE_NAME = "Tasks.db";
        public static final String DATABASE_TABLE = "tasks_table";
        public static final int DATABASE_VERSION = 1;
        public static final String KEY_ID = "_id";
        public static final String TASK = "task";
        public static final String PROJECT = "project";
        public static final String COMPLETE = "complete";
        public static final String START_DATE = "start";
        public static final String FINISH_DATE = "finish";
        public static final String PLANNED = "planned";
        public static final String PROJECTID = "projectid";
        public static final String TASKID = "taskid";
        public static final String COMMENT = "comment";
        public static final String PLANNEDAY = "plannedday";
        public static final String TIMESTAMP = "timestamp";
        public static final String DATE = "date";

    }
}
