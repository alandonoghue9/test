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
        public static final String TASK_KEY_ID = "_id";
        public static final String BYDAY_KEY_ID = "_id";
        public static final String CONFIG_KEY_ID = "_id";
        public static final String TIMESHEET_KEY_ID = "_id";

        public static final String TASK = "task";
        public static final String PROJECT = "project";
        public static final String COMPLETE = "complete";
        public static final String START_DATE = "start";
        public static final String FINISH_DATE = "finish";
        public static final String PLANNED = "planned";
        public static final String PROJECTID = "projectid";
        public static final String TASKID = "taskid";

        public static final String COMMENT = "comment";
        public static final String BYDAY_TASKID = "bydaytaskid";
        public static final String PLANNEDAY = "plannedday";
        public static final String TIMESTAMP = "timestamp";
        public static final String DATE = "date";

        public static final String MAXHOURS = "maxhours";
        public static final String MINHOURS = "minhours";
        public static final String MAXMON = "maxmon";
        public static final String MINMON = "minmon";
        public static final String MAXTUE = "maxtue";
        public static final String MINTUE = "mintue";
        public static final String MAXWED = "maxwed";
        public static final String MINWED = "minwed";
        public static final String MAXTHUR = "maxthur";
        public static final String MINTHUR = "minthur";
        public static final String MAXFRI = "maxfri";
        public static final String MINFRI = "minfri";
        public static final String MAXSAT = "maxsat";
        public static final String MINSAT = "minsat";
        public static final String MAXSUN = "maxsun";
        public static final String MINSUN = "minsun";
        public static final String submission = "submission";

        public static final String PLANNEDHOURS = "plannedhours";
        public static final String NOOFTASKS = "nooftasks";
        public static final String TOTALHOURS = "totalhours";
        public static final String PDFURL = "pdfurl";
        public static final String SUBMITTED = "submitted";
        public static final String MONHOURS = "monhours";
        public static final String TUEHOURS = "tuehours";
        public static final String WEDHOURS = "wedhours";
        public static final String THURHOURS = "thurhours";
        public static final String FRIHOURS = "frihours";
        public static final String SATHOURS = "sathours";
        public static final String SUNHOURS = "sunhours";
    }
}
