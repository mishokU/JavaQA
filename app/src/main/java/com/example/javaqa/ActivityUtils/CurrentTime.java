package com.example.javaqa.ActivityUtils;

import java.text.DateFormat;
import java.util.Calendar;

public class CurrentTime {

  private static Calendar calendar;

  public static String getCurrentDate(){
    calendar = Calendar.getInstance();
    return DateFormat.getDateInstance().format(calendar.getTime());
  }
}
