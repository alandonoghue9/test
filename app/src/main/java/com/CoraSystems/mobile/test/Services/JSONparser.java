package com.CoraSystems.mobile.test.Services;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
import android.util.Log;

import com.CoraSystems.mobile.test.Database.DatabaseReader;

/**
 * Created by Alan on 8/5/2014.
 */

public class JSONparser {
    private String data;
    private Context Parent;
    public static final String TaskList = "TaskList";
    public static final String ByDay = "ByDay";
    public static final String Timesheet = "Timesheet";
    public static final String ConfigItems = "ConfigItems";

    public ArrayList<String> projectIdArrayList = new ArrayList<String>();
    public ArrayList<String> projectDescArrayList = new ArrayList<String>();
    public ArrayList<String> taskIdArraylist = new ArrayList<String>();
    public ArrayList<String> taskDescArrayList = new ArrayList<String>();
    public ArrayList<String> plannedHoursArrayList = new ArrayList<String>();
    public ArrayList<String> startDateArrayList = new ArrayList<String>();
    public ArrayList<String> finishDateArrayList = new ArrayList<String>();
    public ArrayList<String> completeArrayList = new ArrayList<String>();

    public ArrayList<String> commentArraylist = new ArrayList<String>();
    public ArrayList<String> plannedHoursByDayArrayList = new ArrayList<String>();
    public ArrayList<String> hoursArrayList = new ArrayList<String>();
    public ArrayList<String> timestampArrayList = new ArrayList<String>();
    public ArrayList<String> dateArrayList = new ArrayList<String>();
    public ArrayList<String> taskIdArraylist1 = new ArrayList<String>();

    public double plannedHours;
    public int noOfTasks;
    public double totalHours;
    //public double hoursDay7;
    public String pdfUrl;
    public boolean submitted;

    public double mondayHours;
    public double tuesdayHours;
    public double wednesdayHours;
    public double thursdayHours;
    public double fridayHours;
    public double saturdayHours;
    public double sundayHours;


    public double MAXHOURS;
    public double MINHOURS;
    public double MAXMON;
    public double MINMON;
    public double MAXTUE;
    public double MINTUE;
    public double MAXWED;
    public double MINWED;
    public double MAXTHUR;
    public double MINTHUR;
    public double MAXFRI;
    public String MINFRI;
    public String MAXSAT;
    public String MINSAT;
    public String MAXSUN;
    public String MINSUN;
    public String submission;



    public JSONparser(String data, Context Parent) {
        this.data = data;
        this.Parent = Parent;
    }
    public int parsedData() {
         String testData = "<string xmlns=\"http://tempuri.org/ProjectVision/Project\">"+ "{\"TaskList\" : [{\"project id\" : \"1318\",\"project description\" : \"this is to test how long it takes\",\"task id\" : \"14396\"," +
                 "\"task description\" : \"Initial Task\",\"planned hours\" : \"30.00\",\"start date\" : \"2008-09-12 00:00:00\"," +
                 "\"finish date\" : \"2008-10-28 00:00:00\",\"complete\" : \"1.00\"}]}"+"</string>";

        int parsedReturn = 0;
        String[] parts;
        Iterator<String> iter;
        String key;
        JSONArray jsonArray;
        JSONArray daysArray;

        DatabaseReader databaseReader = new DatabaseReader();
        databaseReader.DataSource(Parent);
        databaseReader.open();

        if (testData != null) {
            try {
                parts = testData.split(">");
                testData = parts[1];
                parts = testData.split("<");
                testData = testData + parts[0];

                JSONObject jsonObject =  new JSONObject(testData);
                iter = jsonObject.keys();
                key = iter.next();
                switch(key) {
                    case TaskList:
                        jsonArray = jsonObject.optJSONArray(TaskList);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);

                            projectIdArrayList.add("" + jsonobject.getInt("project id"));
                            projectDescArrayList.add(jsonobject.getString("project description"));
                            taskIdArraylist.add("" + jsonobject.getInt("task id"));
                            taskDescArrayList.add(jsonobject.getString("task description"));
                            plannedHoursArrayList.add(jsonobject.getString("planned hours"));
                            startDateArrayList.add(jsonobject.getString("start date"));
                            finishDateArrayList.add(jsonobject.getString("finish date"));
                            completeArrayList.add("" + jsonobject.getDouble("complete")); }

                        databaseReader.addTask(data, Parent);
                        break;
                    case ByDay:
                        jsonArray = jsonObject.optJSONArray(ByDay);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);

                            commentArraylist.add("" + jsonobject.getInt("comment"));
                            plannedHoursByDayArrayList.add(jsonobject.getString("planned hours"));
                            hoursArrayList.add("" + jsonobject.getInt("hours"));
                            dateArrayList.add(jsonobject.getString("date"));
                            timestampArrayList.add(jsonobject.getString("timestamp"));
                            taskIdArraylist1.add("" + jsonobject.getInt("task id"));

                        }
                        break;
                    case Timesheet:
                        plannedHours = jsonObject.getDouble("planned hours");
                        noOfTasks = jsonObject.getInt("number of tasks");
                        totalHours = jsonObject.getDouble("total hours");
                        daysArray = jsonObject.getJSONArray("hours day");
                        //hoursDay7 = jsonObject.getDouble("hours day 7");
                        pdfUrl = jsonObject.getString("PDF URL");
                        submitted = jsonObject.getBoolean("submitted");

                        mondayHours = daysArray.getDouble(0);
                        tuesdayHours = daysArray.getDouble(1);
                        wednesdayHours = daysArray.getDouble(2);
                        thursdayHours = daysArray.getDouble(3);
                        fridayHours = daysArray.getDouble(4);
                        saturdayHours = daysArray.getDouble(5);
                        sundayHours = daysArray.getDouble(6);

                        break;
                    case ConfigItems:
                        jsonArray = jsonObject.optJSONArray(ConfigItems);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);
                        }
                        break;

                }
            } catch (Exception e) {
                Log.e("System out", "Error "+e.getMessage());
                parsedReturn = -1;
            }


        }
        return parsedReturn;
    }


}
