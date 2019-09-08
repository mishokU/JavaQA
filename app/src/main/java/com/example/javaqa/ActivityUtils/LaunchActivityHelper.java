package com.example.javaqa.ActivityUtils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.example.javaqa.R;
import com.example.javaqa.activities.java_core.IntroductionActivity;

public class LaunchActivityHelper {

    public void launchActivity(Activity fromActivity, Class toActivity, int flags) {
        Intent intent = new Intent(fromActivity,toActivity);
        intent.addFlags(flags);
        fromActivity.startActivity(intent);
    }

    public void JavaCoreIndexingActivityHelper(Activity activity, int index){
        if(index == 0) {
            launchActivity(activity, IntroductionActivity.class, Intent.FLAG_ACTIVITY_NO_ANIMATION);
        } else if(index == 1) {

        } else if(index == 2) {

        } else if(index == 3) {

        } else if(index == 4) {

        } else if(index == 5) {

        } else if(index == 6) {

        } else if(index == 7) {

        }
    }

    public void VCSActivityHelper(Activity activity, int index){

    }

    public void BuildToolsActivityHelper(Activity activity, int index){

    }

    public void DataBasesActivityHelper(Activity activity, int index){

    }
}
