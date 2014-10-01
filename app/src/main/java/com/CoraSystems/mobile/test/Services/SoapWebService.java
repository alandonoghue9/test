package com.CoraSystems.mobile.test.Services;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.CoraSystems.mobile.test.Objects.ByDay;
import com.CoraSystems.mobile.test.Objects.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Alan on 8/5/2014.
 */
public class SoapWebService implements Serializable{
    public static final String TAG = "json";
    private static final long serialVersionUID = 1L;
    private String uName;
    private String Pwd;
    private Context context;
    private String BaseURL;
    private String SerialCloseTag;
    private String SerialOpenTag2;

    public SoapWebService(String uName, String Pwd, Activity context) {
        this.uName = uName;
        this.Pwd = Pwd;
        this.context = context;
        SetIPAddress();
        this.SerialOpenTag2 ="<string xmlns=\"http://tempuri.org/ProjectVision/Project\">"; //User for replace on return data
        this.SerialCloseTag = "</string>";

    }
    public SoapWebService(String uName, String Pwd) {
        this.uName = uName;
        this.Pwd = Pwd;
        SetIPAddress();
        this.SerialOpenTag2 ="<string xmlns=\"http://tempuri.org/ProjectVision/Project\">"; //User for replace on return data
        this.SerialCloseTag = "</string>";
    }
    private void SetIPAddress()
    {
        try
        {
            this.BaseURL = User.getBaseURL();//"http://46.17.93.112/MobileTest/dataservice/";
        }
        catch (Exception ex)
        {
            this.BaseURL = "http://46.17.93.112/MobileTest/DataService/";


        }
    }

    public String SendThisData(String strTextToSend, int Timeout, String urlEnd) throws IOException
    {
        String strRetVal = "";
        String bytessend;
        try
        {
            StringBuilder sb = new StringBuilder();

            bytessend = this.SerialOpenTag2 + strTextToSend + this.SerialCloseTag;

            URL url = new URL(this.BaseURL+urlEnd);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setUseCaches (false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setConnectTimeout( Timeout );  // long timeout, but not infinite
            con.setReadTimeout( Timeout );
            con.setRequestProperty ( "Content-Type", "application/soap+xml; charset=utf-8");

            con.connect();
            Log.i(TAG, "successful connection");
            DataOutputStream wr = new DataOutputStream (con.getOutputStream ());
            wr.writeBytes(bytessend);
            wr.flush ();
            wr.close ();
            Log.i(TAG, "sent data "+con.getResponseCode());
            InputStreamReader ISReader = new InputStreamReader(con.getInputStream());
            BufferedReader in = new BufferedReader(ISReader, 8192000);

            String inputLine;
            Log.i(TAG, "read data");
            while ((inputLine = in.readLine()) != null)
            {
                sb.append(inputLine);
            }
            ISReader.close();
            in.close();
            con.disconnect();

            strRetVal = sb.toString();
            Log.i(TAG, strRetVal);
        }
        catch(Exception ex)
        {
            Log.i(TAG, "exception occurred "+ex.toString());

        }
        Log.i(TAG,"final tag: " +strRetVal);
        return strRetVal;
    }

    public String ConvertDataToHexString(String strData) {
        String strRetVal ="";
        strRetVal = Integer.toHexString(Integer.parseInt(strData));

        return strRetVal;
    }

    public String getTaskFromServer(String start, String end, String urlEnder){
        StringBuffer taskToJson = new StringBuffer();
        String responseStr="";
        taskToJson.append("{\"Users\" : [{\"userName\":\"" + uName + "\",\"password\":\"" +Pwd + "\"");
        taskToJson.append(",\"start_date\":\"" + start + "\",\"finish_date\":\"" + end + "\"");
        taskToJson.append("}]}");
        try
        {
            responseStr = SendThisData(taskToJson.toString(),  450000, urlEnder);
            responseStr = CleanResponseString(responseStr);

            Log.i("webservices", responseStr);
        }
        catch (IOException e)
        {
            Log.e("System out", "Error IO " + e.getMessage());
        }

        if(responseStr.contains("User could not be validated")){
            return "User could not be validated";}
        else if(responseStr.equals("")){
            return "No Data Recieved";}

        JSONparser jsoNparser = new JSONparser(responseStr, this.context);
        try {
            jsoNparser.parsedData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseStr;
    }
    public String sendSummit(String start, String sumitee, String urlEnder){
        StringBuffer taskToJson = new StringBuffer();
        String responseStr="";
        taskToJson.append("{\"Users\" : [{\"userName\":\"" + uName + "\",\"password\":\"" +Pwd + "\"");
        taskToJson.append(",\"start_date\":\"" + start + "\",\"submit\":\"" + sumitee + "\"");
        taskToJson.append("}]}");
        try
        {
            responseStr = SendThisData(taskToJson.toString(),  450000, urlEnder);
            responseStr = CleanResponseString(responseStr);
            Log.i("webservices", responseStr);
        }
        catch (IOException e)
        {
            Log.e("System out", "Error IO " + e.getMessage());
        }

        if(responseStr.contains("User could not be validated")){
            return "User could not be validated";}
        else if(responseStr.equals("")){
            return "No Data Recieved";}

        JSONparser jsoNparser = new JSONparser(responseStr, this.context);
        try {
            jsoNparser.parsedData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseStr;
    }
    public String sendServer(String start, String end){
        StringBuffer taskToJson = new StringBuffer();
        String responseStr="";
        taskToJson.append("{\"Users\" : [{\"userName\":\"" + uName + "\",\"password\":\"" +Pwd + "\"");
        taskToJson.append(",\"start_date\":\"" + start + "\",\"finish_date\":\"" + end + "\"");
        taskToJson.append("}]}");
        try
        {
            responseStr = SendThisData(taskToJson.toString(),  450000, "");
            responseStr = CleanResponseString(responseStr);
        }
        catch (IOException e)
        {
            Log.e("System out", "Error IO " + e.getMessage());
        }

        if(responseStr.contains("User could not be validated")){
            return "User could not be validated";}
        else if(responseStr.equals("")){
            return "No Data Recieved";}

        JSONparser jsoNparser = new JSONparser(responseStr, this.context);
        try {
            jsoNparser.parsedData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseStr;
    }
    public String sendByDayLocalSave(ArrayList<ByDay> localSaves){
        String responseStr="";
        JSONObject object = new JSONObject();
        JSONObject objectUser = new JSONObject();
        JSONObject objectwhole = new JSONObject();
        JSONObject obj = null;
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArrayUser = new JSONArray();


            for(int i = 0; i < 2; i++){
                obj = new JSONObject();
                try{
                    obj.put("date", "2014-08-23");
                    obj.put("comment", "this is a comment");
                    obj.put("hours_worked", ""+Double.valueOf(6.5));
                    obj.put("timestamp", "2014-03-04 07:00:00");
                    obj.put("actual_id", ""+Integer.valueOf(1234));
                }
                catch (JSONException e) {
                    e.printStackTrace();}

                jsonArray.put(obj);}
                obj = new JSONObject();
        try{
            objectUser.put("userName", uName);
            objectUser.put("password", Pwd);
            jsonArrayUser.put(objectUser);
            object.put("Users", jsonArrayUser);

            object.put("percentage_complete", ""+Double.valueOf(0.75));
            object.put("timestamp", "2014-03-04 07:00:00");
            object.put("task_id", ""+Integer.valueOf(234));
            object.put("Day_Effort", jsonArray);

            objectwhole.put("SaveTasks", object);}
        catch (JSONException e){
            e.printStackTrace();}

        try
        {
            responseStr = SendThisData(object.toString(),  450000, "SaveTasks");
            responseStr = CleanResponseString(responseStr);
        }
        catch (IOException e)
        {
            Log.e("System out", "Error IO " + e.getMessage());
        }

        if(responseStr.contains("User could not be validated")){
            return "User could not be validated";}
        else if(responseStr.equals("")){
            return "No Data Recieved";}

        JSONparser jsoNparser = new JSONparser(responseStr, this.context);
        try {
            jsoNparser.parsedData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseStr;
    }

    public String getConfigFromServer(){
        StringBuffer configToJson = new StringBuffer();
        String responseStr="";
        configToJson.append("{\"Users\" : [{\"userName\":\"" + uName + "\",\"password\":\"" +Pwd + "\"");
        configToJson.append("}]}");
        try
        {
            responseStr = SendThisData(configToJson.toString(),  450000, "ConfigItems");
            responseStr = CleanResponseString(responseStr);
        }
        catch (IOException e)
        {
            Log.e("System out", "Error IO " + e.getMessage());
        }

        if(responseStr.contains("User could not be validated")){
            return "User could not be validated";}
        else if(responseStr.equals("")){
            return "No Data Recieved";}

        JSONparser jsoNparser = new JSONparser(responseStr, this.context);
        try {
            jsoNparser.parsedData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseStr;
    }
    public String getTimeSheetStatus(){
        StringBuffer configToJson = new StringBuffer();
        String responseStr="";
        configToJson.append("{\"Users\" : [{\"userName\":\"" + uName + "\",\"password\":\"" +Pwd + "\"");
        configToJson.append("}]}");
        try
        {
            responseStr = SendThisData(configToJson.toString(),  450000, "TimeSheetStatus");
            responseStr = CleanResponseString(responseStr);
        }
        catch (IOException e)
        {
            Log.e("System out", "Error IO " + e.getMessage());
        }
        JSONparser jsoNparser = new JSONparser(responseStr, this.context);
        try {
            jsoNparser.parsedData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseStr;
    }

    public String CleanResponseString(String strResponse)
    {
        if (strResponse.contains("<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">\"")){
            strResponse = strResponse.replace("<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">\"", "");
        }
        if (strResponse.contains(this.SerialCloseTag)){
            strResponse = strResponse.replace(this.SerialCloseTag, "");
        }
        if (strResponse.contains("\\")){
        strResponse = strResponse.replace("\\", "");
    }
        if (strResponse.contains(";")){
            strResponse = strResponse.replace(";", "");
        }
        return strResponse;}
}
