package com.example.javaqa.repository.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnection {

  private ConnectivityManager connectivityManager;

  public InternetConnection(Context context){
    connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
  }

  public boolean isConnected(){
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    if(networkInfo != null && networkInfo.isConnected()) {
      return true;
    }
    return false;
  }
}
