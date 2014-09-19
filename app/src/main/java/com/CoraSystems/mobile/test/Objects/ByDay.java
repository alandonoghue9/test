package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 8/24/2014.
 */
public class ByDay {
    private int id;
    private String comment;
    private double plannedHours;
    private double hours;
    private String timestamp;
    private String  date;
    private int taskId;
    private  int actualId;

    public ByDay(int dbId, String commentByDay, double plannedhours, double hoursByDay, String timestampByDay, String dateByDay, int taskIdByDay, int actualID){
        id = dbId;
        comment=commentByDay;
        plannedHours = plannedhours;
        hours = hoursByDay;
        timestamp = timestampByDay;
        date  = dateByDay;
        taskId = taskIdByDay;
        actualId = actualID;
    }
    public int getId(){return id;}
    public int gettaskId(){return taskId;}
    public int getActualId(){return actualId;}
    public double getPlannedHours(){return plannedHours;}
    public double getHours(){return hours;}
    public String getTimestamp(){return timestamp;}
    public String getDate(){return date;}
    public String getComment(){return comment;}

}
