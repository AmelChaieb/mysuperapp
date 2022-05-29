package com.example.mysupervisorapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {


    SharedPreferences.Editor editor;
    SharedPreferences userSession;

    Context context;

    public static final String SESSION_USERSESSION="userLoginSession";
    public static final String SESSION_REMEMBERME="rememberMe";

    //userSession variables

    private static final String IS_LOGIN="IsLoggedIn";
    public static final String KEY_FULLNAME="name";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_PHONE="phoneNo";
    public static final String KEY_STATUT="statut";


    //remember me variables
    private static final String IS_REMEMBERME="IsRememberMe";
    public static final String KEY_SESSIONNAME="name";
    public static final String KEY_SESSIONPASSWORD="password";


    public SessionManager(Context _context, String sessionName){
        context =_context;
        userSession=context.getSharedPreferences(sessionName,Context.MODE_PRIVATE);
        editor=userSession.edit();


    }

    /* users
    login
    session
     */

    public void createLoginSession(String name, String email, String password, String phoneNo, String statut){

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_FULLNAME,name);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASSWORD,password);
        editor.putString(KEY_PHONE,phoneNo);
        editor.putString(KEY_STATUT,statut);

        editor.commit();
    }

    public HashMap<String,String> getUserDetailFromSession(){
        HashMap<String,String> userData=new HashMap<>();
        userData.put(KEY_FULLNAME,userSession.getString(KEY_FULLNAME,null));
        userData.put(KEY_EMAIL,userSession.getString(KEY_EMAIL,null));
        userData.put(KEY_PASSWORD,userSession.getString(KEY_PASSWORD,null));
        userData.put(KEY_PHONE,userSession.getString(KEY_PHONE,null));
        userData.put(KEY_STATUT,userSession.getString(KEY_STATUT,null));

        return userData;

    }

    public Boolean checkLogin(){
        return userSession.getBoolean(IS_LOGIN, true);
    }

    public void logoutUserFormSession(){
        editor.clear();
        editor.commit();

    }


     /* remember
     me
    session
    functions
     */

    public void createRememberMeSession(String name,  String password){

        editor.putBoolean(IS_REMEMBERME, true);
        editor.putString(KEY_SESSIONNAME,name);
        editor.putString(KEY_SESSIONPASSWORD,password);


        editor.commit();
    }

    public HashMap<String,String> getRemeberMeDetailsFromSession(){


        HashMap<String,String> userData=new HashMap<>();
        userData.put(KEY_SESSIONNAME,userSession.getString(KEY_SESSIONNAME,null));
        userData.put(KEY_SESSIONPASSWORD,userSession.getString(KEY_SESSIONPASSWORD,null));


        return userData;

    }


    public Boolean checkRememberMe(){
        if ((userSession.getBoolean(IS_REMEMBERME, false))){
            return true;
        }
        else {
            return false;
        }


    }


}
