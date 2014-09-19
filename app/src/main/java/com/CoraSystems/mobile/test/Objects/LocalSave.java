package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 9/8/2014.
 */
public class LocalSave {
    private int id;
    private String comment;
    private double complete;
    private double hoursWorked;
    private String timestamp;
    private String  date;
    private int taskId;
    private  int actualId;


    public LocalSave(int dbId, String commentByDay,double completeer, double hoursByDay, String timestampByDay, String dateByDay, int taskIdByDay, int actualID){
        id = dbId;
        comment = commentByDay;
        complete = completeer;
        hoursWorked = hoursByDay;
        timestamp = timestampByDay;
        date  = dateByDay;
        taskId = taskIdByDay;
        actualId = actualID;

    }
    public int getLocalId(){return id;}
    public int gettaskId(){return taskId;}
    public int getActualLocalId(){return actualId;}
    public double getCompleteLocal(){return complete;}
    public double getHours(){return hoursWorked  ;}
    public String getTimestamp(){return timestamp;}
    public String getDate(){return date;}
    public String getComment(){return comment;}

}
