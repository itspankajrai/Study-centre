package com.Rai.studycenter.utils;

import android.app.Activity;
import android.content.Intent;

import com.Rai.studycenter.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utils  
{  
     private static int sTheme;  
     public final static int THEME_DEFAULT = 0;  
     public final static int THEME_WHITE = 1;  
     public final static int THEME_BLUE = 2;  
     /** 
      * Set the theme of the Activity, and restart it by creating a new Activity of the same type. 
      */  
     public static void changeToTheme(Activity activity, int theme)  
     {  
          sTheme = theme;  
          activity.finish();  
activity.startActivity(new Intent(activity, activity.getClass()));  
     }  
     /** Set the theme of the activity, according to the configuration. */  
     public static void onActivityCreateSetTheme(Activity activity)  
     {  
          switch (sTheme)  
          {  
          default:  
          case THEME_DEFAULT:  
              activity.setTheme(R.style.AppTheme);
              break;  
          case THEME_WHITE:  
              activity.setTheme(R.style.AppGreen);
              break;  
          case THEME_BLUE:  
              activity.setTheme(R.style.AppTheme);
              break;  
          }  
     }
     public String getUniqueCollegeID(){
         Calendar cal = new GregorianCalendar();
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
         String unique_id = df.format(cal.getTime()).replaceAll("-","").trim();
        return unique_id;
     }
}  