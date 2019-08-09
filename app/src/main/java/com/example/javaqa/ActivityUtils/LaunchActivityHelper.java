package com.example.javaqa.ActivityUtils;

import android.app.Activity;
import android.content.Intent;

public class LaunchActivityHelper {

    public LaunchActivityHelper(Activity fromActivity, Class toActivity, int flags) {
        Intent intent = new Intent(fromActivity,toActivity);
        intent.addFlags(flags);
        fromActivity.startActivity(intent);
    }
}
