package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 9/18/2014.
 */
public class User{
public static int id;
public static String user;
public static String password;
public static String baseUrl;


        public User(int dbId, String User, String Password, String BaseUrl){
            id = dbId;
            user = User;
            password = Password;
            baseUrl = BaseUrl;
        }

        public static int getID(){return id;}
        public static String getUser(){return user;}
        public static String getPassword(){return password;}
        public static String getBaseURL(){return baseUrl;}

        public static void setBaseURL(String URL){
            baseUrl = URL;
        }
}
