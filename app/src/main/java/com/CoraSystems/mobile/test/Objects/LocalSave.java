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


    public LocalSave(int dbId, String commentByDay,double completeer, double hoursByDay, String timestampByDay, String dateByDay, int taskIdByDay){
        id = dbId;
        comment = commentByDay;
        complete = completeer;
        hoursWorked = hoursByDay;
        timestamp = timestampByDay;
        date  = dateByDay;
        taskId = taskIdByDay;


    }
    public int getLocalId(){return id;}
    public int gettaskId(){return taskId;}
    public double getCompleteLocal(){return complete;}
    public double getHours(){return hoursWorked  ;}
    public String getTimestamp(){return timestamp;}
    public String getDate(){return date;}
    public String getComment(){return comment;}

}
