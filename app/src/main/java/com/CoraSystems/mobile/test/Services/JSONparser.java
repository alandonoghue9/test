package com.CoraSystems.mobile.test.Services;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
import android.util.Log;

import com.CoraSystems.mobile.test.database.DatabaseReader;

/**
 * Created by Alan on 8/5/2014.
 */

public class JSONparser {
    private String data;
    private Context Parent;
    private int k;
    public static final String TaskList = "TaskList";
    public static final String ByDay = "ByDay";
    public static final String Timesheet = "Timesheet";
    public static final String ConfigItems = "ConfigItems";






    public JSONparser(String data, Context Parent, int k) {
        this.data = data;
        this.Parent = Parent;
        this.k = k;
    }
    public int parsedData() {
        String testData;
        if (k == 0) {
            /*testData = "<string xmlns=\"http://tempuri.org/ProjectVision/Project\">" + "{\"TaskList\" : [{\"project id\" : \"1318\",\"project description\" : \"this is to test how long it takes\",\"task id\" : \"14396\"," +
                    "\"task description\" : \"Initial Task\",\"planned hours\" : \"30.00\",\"start date\" : \"2008-09-12 00:00:00\"," +
                    "\"finish date\" : \"2008-10-28 00:00:00\",\"complete\" : \"1.00\"}]}" + "</string>";*/
            testData ="{\"TaskList\" : [{\"project id\" : \"11318\",\"project description\" : \"this is to test how long it takes\",\"task id\" : \"143396\"," +
                    "\"task description\" : \"Initial Task\",\"planned hours\" : \"35.00\",\"start date\" : \"2008-09-22 00:00:00\",\"finish date\" : \"2008-10-18 00:00:00\",\"complete\" : \"1.00\"},{\"project id\" : \"13618\",\"project description\" : \"this is to test how long it takes\",\"task id\" : \"14396\",\"task description\" : \"Initial Task\",\"planned hours\" : \"30.00\",\"start date\" : \"2008-09-12 00:00:00\",\"finish date\" : \"2008-10-28 00:00:00\",\"complete\" : \"1.00\"}]}";
        }else {
            testData ="{\"ConfigItems\" : [{\"minhours\" : \"37.50\",\"maxhours\" : \"40.00\",\"maxsun\" : \"0.00\",\"maxmon\" : \"24.00\",\"maxtue\" : \"24.00\",\"maxwed\" : \"24.00\",\"maxthu\" : \"24.00\",\"maxfri\" : \"24.00\",\"maxsat\" : \"0.00\"}]}";
        }
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
                JSONObject jsonObject =  new JSONObject(testData);
                iter = jsonObject.keys();
                key = iter.next();
                switch(key) {
                    case TaskList:
                        jsonArray = jsonObject.optJSONArray(TaskList);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);

                            databaseReader.projectIdArrayList.add("" + jsonobject.getInt("project id"));
                            databaseReader.projectDescArrayList.add(jsonobject.getString("project description"));
                            databaseReader.taskIdArraylist.add("" + jsonobject.getInt("task id"));
                            databaseReader.taskDescArrayList.add(jsonobject.getString("task description"));
                            databaseReader.plannedHoursArrayList.add(jsonobject.getString("planned hours"));
                            databaseReader.startDateArrayList.add(jsonobject.getString("start date"));
                            databaseReader.finishDateArrayList.add(jsonobject.getString("finish date"));
                            databaseReader.completeArrayList.add("" + jsonobject.getDouble("complete")); }

                        databaseReader.addTask(data, Parent);
                        break;
                    case ByDay:
                        jsonArray = jsonObject.optJSONArray(ByDay);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);

                            databaseReader.commentArraylist.add("" + jsonobject.getInt("comment"));
                            databaseReader.plannedHoursByDayArrayList.add(jsonobject.getString("planned hours"));
                            databaseReader.hoursArrayList.add("" + jsonobject.getInt("hours"));
                            databaseReader.dateArrayList.add(jsonobject.getString("date"));
                            databaseReader.timestampArrayList.add(jsonobject.getString("timestamp"));
                            databaseReader.taskIdArraylist1.add("" + jsonobject.getInt("task id"));

                        }
                        break;
                    case Timesheet:
                        databaseReader.plannedHours = jsonObject.getDouble("planned hours");
                        databaseReader.noOfTasks = jsonObject.getInt("number of tasks");
                        databaseReader.totalHours = jsonObject.getDouble("total hours");
                        daysArray = jsonObject.getJSONArray("hours day");
                        databaseReader.pdfUrl = jsonObject.getString("PDF URL");
                        databaseReader.submitted = jsonObject.getBoolean("submitted");

                        databaseReader.mondayHours = daysArray.getDouble(0);
                        databaseReader.tuesdayHours = daysArray.getDouble(1);
                        databaseReader.wednesdayHours = daysArray.getDouble(2);
                        databaseReader.thursdayHours = daysArray.getDouble(3);
                        databaseReader.fridayHours = daysArray.getDouble(4);
                        databaseReader.saturdayHours = daysArray.getDouble(5);
                        databaseReader.sundayHours = daysArray.getDouble(6);

                        break;
                    case ConfigItems:
                        jsonArray = jsonObject.optJSONArray(ConfigItems);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);

                            databaseReader.MAXHOURS= jsonobject.getDouble("maxhours");
                            databaseReader.MINHOURS= jsonobject.getDouble("minhours");
                            databaseReader.MAXMON= jsonobject.getDouble("maxmon");
                            databaseReader.MINMON= jsonobject.getDouble("maxmon");
                            databaseReader.MAXTUE= jsonobject.getDouble("maxtue");
                            databaseReader.MINTUE= jsonobject.getDouble("maxtue");
                            databaseReader.MAXWED= jsonobject.getDouble("maxwed");
                            databaseReader.MINWED= jsonobject.getDouble("maxwed");
                            databaseReader.MAXTHUR= jsonobject.getDouble("maxthu");
                            databaseReader.MINTHUR= jsonobject.getDouble("maxthu");
                            databaseReader.MAXFRI= jsonobject.getDouble("maxfri");
                            databaseReader.MINFRI= jsonobject.getDouble("maxfri");
                            databaseReader.MAXSAT= jsonobject.getDouble("maxsat");
                            databaseReader.MINSAT= jsonobject.getDouble("maxsat");
                            databaseReader.MAXSUN= jsonobject.getDouble("maxsun");
                            databaseReader.MINSUN= jsonobject.getDouble("maxsun");
                            //submission = jsonobject.getString("start date");
                            databaseReader.submission =  "hello";

                            databaseReader.addConfig(data, Parent);
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
