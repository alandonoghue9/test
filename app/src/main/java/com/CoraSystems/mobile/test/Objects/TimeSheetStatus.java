package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 9/18/2014.
 */
public class TimeSheetStatus {
    private int id;
    private int timeSheetId;
    private String startDate;
    private String finishDate;
    private String status;
    private double statusId;
    private double statusDescription;

    public TimeSheetStatus(int dbId, int timeSheetID, String start, String finish, String stats,
                     double statId, double statusDesc){
        id = dbId;
        timeSheetId = timeSheetID;
        startDate = start;
        finishDate = finish;
        status = stats;
        statusId  = statId;
        statusDescription = statusDesc;
    }

    public int getID(){return id;}
    public double getTimeSheetId(){return timeSheetId;}
    public String getstartDate(){return startDate;}
    public String getfinishDate(){return finishDate;}
    public String getstatus(){return status;}
    public double getstatusId(){return statusId;}
    public double gestatusDescription(){return statusDescription;}
}
