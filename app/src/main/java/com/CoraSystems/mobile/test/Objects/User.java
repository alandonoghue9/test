package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 9/18/2014.
 */
public class User{
private int id;
private String user;
private String password;
private String baseUrl;


        public User(int dbId, String User, String Password, String BaseUrl){
            id = dbId;
            user = User;
            password = Password;
            baseUrl = BaseUrl;
        }

        public int getID(){return id;}
        public String getstartDate(){return user;}
        public String getfinishDate(){return password;}
        public String getstatus(){return baseUrl;}
}
