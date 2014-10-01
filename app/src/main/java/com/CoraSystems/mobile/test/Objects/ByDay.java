package com.CoraSystems.mobile.test.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alan on 8/24/2014.
 */
public class ByDay implements Parcelable {
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

    /** NEEDS TO BE MADE PARCELABLE TO PASS BETWEEN ACTIVITY AND FRAGMENT (this is used to pass data from the Timesheet activity to the TimesheetDays fragment) **/
    public ByDay(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<ByDay> CREATOR = new Parcelable.Creator<ByDay>() {
        public ByDay createFromParcel(Parcel in) {
            return new ByDay(in);
        }

        public ByDay[] newArray(int size) {

            return new ByDay[size];
        }

    };

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        comment=in.readString();
        plannedHours = in.readDouble();
        hours = in.readDouble();
        timestamp = in.readString();
        date  = in.readString();
        taskId = in.readInt();
        actualId = in.readInt();
    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(comment);
        dest.writeDouble(plannedHours);
        dest.writeDouble(hours);
        dest.writeString(timestamp);
        dest.writeString(date);
        dest.writeInt(taskId);
        dest.writeInt(actualId);
    }
}
