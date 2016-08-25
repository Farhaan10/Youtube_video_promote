package com.example.farhaan.youtube_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
        int number = sharedPref.getInt("isLogged", 0);
        if(number == 0) {
            //Open the login activity and set this so that next it value is 1 then this conditin will be false.
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putInt("isLogged",1);
            prefEditor.commit();
            Intent intent = new Intent(MainActivity.this, SignIn.class);
            startActivity(intent);
        } else {
            Values.name = sharedPref.getString("name","");
            Values.email = sharedPref.getString("email","");
            System.out.println("In Values: name = " + Values.name);
            Intent intent1 = new Intent(MainActivity.this, Holder.class);
            startActivity(intent1);
        }
    }
}