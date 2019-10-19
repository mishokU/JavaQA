package com.example.javaqa.ActivityUtils;

import android.app.Activity;
import android.content.Intent;

import com.example.javaqa.ui.activities.learning.java_core.IntroductionActivity;

public class LaunchActivityHelper {

    private static LaunchActivityHelper instance;

    public static LaunchActivityHelper getInstance(){
        if(instance == null){
            instance = new LaunchActivityHelper();
        }
        return instance;
    }

    private void launchActivity(Activity fromActivity, Class toActivity, int flags) {
        Intent intent = new Intent(fromActivity,toActivity);
        intent.addFlags(flags);
        fromActivity.startActivity(intent);
    }

    public void JavaCoreIndexingActivityHelper(Activity activity, int index){
        if(index == 0) {
            launchActivity(activity, IntroductionActivity.class, Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
    }

    public void VCSActivityHelper(Activity activity, int index){

    }

    public void BuildToolsActivityHelper(Activity activity, int index){

    }

    public void DataBasesActivityHelper(Activity activity, int index){

    }
}
